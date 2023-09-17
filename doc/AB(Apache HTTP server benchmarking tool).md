# AB(Apache HTTP server benchmarking tool)



AB(Apache HTTP server benchmarking tool)는 커맨드 라인을 활용한 매우 가볍고 유용한 웹서버 벤치마킹 도구 이다.

간단한 REST API나 정적 컨텐츠에 대한 성능 테스트 시에 빠르고 간편하게 벤치마킹 정보를 얻어올 수 있다.



## AB 어떤 경우에 사용하나

원래 웹서버 쪽 성능 측정을 위해 만들어진 도구이다보니 세션을 사용한 복잡한 인증절차등을 포함한 페이지를 테스트하기에는 약간 부적합해보이고 간단한 API(헤더 정보는 활용할 수 있으니)나 정적컨텐츠의 서비스 성능 측정에는 적합해 보인다.

웹서버가 설치되어 있는 머신의 한계치를 측정하거나 튜닝할 때나 간단하게 response time 의 성능 측정 정보만 측정해야하는 경우에 매우 유용할 것 같다.

## AB 사용 시 참고사항

`AB`는 익히 알려진 참고할 만한 이슈가 몇가지 있다.

- HTML이나 CSS, image등은 해석하지 않는다. 단순히 response 시간만 나타내준다.
- `HTTP 1.0` 클라이언트를 사용한다.
- 동적 페이지는 `Content-Length` 헤더 내용을 미리 작성할 수 없기 때문에 `-k KeepAlive` 옵션이 동작하지 않는다.
- `HTTP 1.0` 클라이언트이기 때문에 `Transfer-Encoding: chunked` 옵션은 사용할 수 없다.
- `request`간에 delay를 주는 옵션은 없으므로 `DDOS attack`으로 간주 될 수 있다.

## AB를 활용한 벤치마킹 테스트

커맨드라인 툴이기 때문에 사용방법은 매우 간단하다.

```bash
$ ab
Usage: ab [options] [http[s]://]hostname[:port]/path
```

### 옵션

자주 쓰이는 옵션은 아래와 같다.

| 옵션 | 설명                                                         |
| ---- | ------------------------------------------------------------ |
| -n   | 성능을 검사하기위해 보내는 요청수. 기본값으로 요청을 한번만 보내기때문에 일반적인 성능검사 결과를 얻을 수 없다. |
| -c   | 동시에 요청하는 요청수. 기본적으로 한번에 한 요청만을 보낸다. |
| -g   | 측정한 모든 값을 'gnuplot' 혹은 TSV (Tab separate values, 탭으로 구분한 값) 파일에 기록한다. 라벨은 output 파일의 첫번째 라인을 참고한다. |
| -t   | 성능을 검사하는 최대 초단위 시간. 내부적으로 -n 50000을 가정한다. 정해진 시간동안 서버 성능을 검사할때 사용한다. 기본적으로 시간제한 없이 검사한다. |
| -v   | 출력 수준을 지정한다. 4 이상이면 헤더에 대한 정보를, 3 이상이면 (404, 202, 등) 응답코드를, 2 이상이면 경고(warning)와 정보(info)를 출력한다. |
| -A   | 프록시를 통해 BASIC Authentication 정보를 제공한다. :로 구분한 사용자명과 암호를 **base64 인코딩**하여 전송한다. |
| -X   | proxy[:port] 프록시 서버를 사용하여 요청한다.                |

더 다양한 옵션은 [man page](https://httpd.apache.org/docs/trunk/ko/programs/ab.html)를 확인하면 된다.

처음에 조금 혼동한 옵션은 `-c` 옵션인데, 기타 다른 부하툴과는 다른게 동작한다.

```bash
$ ab -n 10 -c 3 http://www.google.com
```

해당 명령어를 실행하면 3개의 프로세스(쓰레드)가 10개씩 쏴주는게 아니라 3개의 풀을 가지고 10번 요청을 진행한다.

`Pool` 개념으로 보면 될 것 같고, 예를 들어보면

```bash
[1, 2, 3]
```

시작을 3개의 요청을 시작했고, 만약 `2번`이 먼저 종료됐으면 `2번`을 `4번`으로 대체하여 요청한다.

```bash
[1, 4, 3]
```

그리고 `1번`이 종료되면 `5번`으로 변경되고

```bash
[5, 4, 3]
```

이제 `3번`이 종료되면

```bash
[5, 4, 6]
```

이런식으로 10개의 요청을 순차로 처리한다.

### AB 실행 결과

이제 실제로 테스트를 진행해보자. 아래의 샘플은 `http://www.google.com/index.html`파일에 대해서 10개의 동시요청으로 총 100개의 요청을 벤치마킹한 결과이다.

```bash
$ ab -n 100 -c 10 -g result.plot http://www.google.com/index.html
This is ApacheBench, Version 2.3 <$Revision: 1706008 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking www.google.com (be patient).....done


Server Software:
Server Hostname:        www.google.com
Server Port:            80

Document Path:          /index.html
Document Length:        271 bytes

Concurrency Level:      10
Time taken for tests:   9.019 seconds
Complete requests:      100
Failed requests:        2
   (Connect: 0, Receive: 0, Length: 2, Exceptions: 0)
Non-2xx responses:      100
Total transferred:      49692 bytes
HTML transferred:       27096 bytes
Requests per second:    11.09 [#/sec] (mean)
Time per request:       901.890 [ms] (mean)
Time per request:       90.189 [ms] (mean, across all concurrent requests)
Transfer rate:          5.38 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:       75   89  11.7     86     136
Processing:    80  763 143.0    799     900
Waiting:       75  441 231.8    445     875
Total:        180  852 143.0    886    1000

Percentage of the requests served within a certain time (ms)
  50%    886
  66%    900
  75%    909
  80%    919
  90%    953
  95%    981
  98%    995
  99%   1000
 100%   1000 (longest request)
```

결과를 보는데 큰 무리는 없으나 혼동이 올 수 있는 부분이 `Time per request` 부분이다.

```bash
Time taken for tests:   9.019 seconds
...
[1]Time per request:       901.890 [ms] (mean)
[2]Time per request:       90.189 [ms] (mean, across all 
...
```

100개의 요청을 전체 처리한 시간은 `9.019s`이다. 그럼으로 1개의 요청 처리 시간 평균 값은 [2]번의 `90.189ms (9.019s / 100)`이다.

[1]번의 결과는 1개의 요청에 대하여 동시요청(여기서는 `-c 10`)이 되었을 때의 전체 평균 값이다. 달리 말하면 동시요청이 되지 않을 경우(1개의 요청) 요청에 대한 전체 평균 값으로 볼 수 있다.

위처럼 명령어 하나 만으로 아주 쉽게 벤치마킹 결과를 확인해 볼 수 있다.

### gnuplot 데이터를 활용한 요청 로그 그래프 그리기

[gnuplot](http://www.gnuplot.info/)은 Command-line 기반의 그래픽 유틸리티로 2D나 3D 그래프를 쉽게 그려줄 수 있다. (멀티 OS를 지원하니 먼저 설치가 되어야 한다.)

`AB`는 간략하게 결과는 바로 확인해 볼 수 있으나 디테일한 각 요청에 대한 로그 값을 원하는 경우 `-g [data_file]` 옵션을 사용해 [gnuplot](http://www.gnuplot.info/) 형식으로 데이터를 남길 수 있다.

#### response time 2D 그래프 그리기

`-g` 옵션을 통해 `result.plot` 파일을 생성했다.

```bash
$ ab -n 100 -c 10 -g result.plot http://www.google.com/index.html
```

`result.plot` 파일의 내용은 다음과 같다.

```bash
starttime	seconds	ctime	dtime	ttime	wait
Tue Jun 14 15:39:59 2016	1465886399	100	80	180	80
Tue Jun 14 15:39:59 2016	1465886399	102	100	201	100
Tue Jun 14 15:39:59 2016	1465886399	102	202	304	201
Tue Jun 14 15:39:59 2016	1465886399	78	305	383	304
Tue Jun 14 15:39:58 2016	1465886398	76	383	459	383
Tue Jun 14 15:39:58 2016	1465886398	75	459	534	459
Tue Jun 14 15:39:58 2016	1465886398	104	534	638	534
Tue Jun 14 15:39:58 2016	1465886398	89	638	727	638
...
```

참고로 컬럼 별로 의미하는 값은 다음과 같다.

- **starttime** : request가 시작된 시간

- **seconds** : starttime을 unix timestamp로 표현

- **ctime** : connection 시간으로 request를 write하기 위해 서버와 socket을 여는 시간

- dtime

   

  : processing 시간 -> 결과를 반환받기 위해 wait하는 시간 + 서버 작업 시간 + 결과 반환 시간.

  - dtime = ttime – ctime

- **ttime** : request가 전체 수행된 시간(ttime = ctime + dtime)

- wait

   

  : request를 보내고나서 response를 받기 전까지 서버사이드에서 처리되는 시간

  - network 시간 = dtime – wait

이제 `gnuplot`을 실행하고 해당 데이터를 아래의 `script`를 실행하거나 `script`를 파일로 저장해두고 `gnuplot script.plot`으로 해당 스크립트를 실행한다.

```bash
# 터미널 사이즈 조정(이미지 사이즈)
set terminal png size 1024,768

# 가로, 세로 비율
set size 1,0.5

# 결과 파일 설정
set output "result.png"

# 범례/key 위치
set key left top

# y축 grid line
set grid y

# Label the x-axis
set xlabel 'requests'

# Label the y-axis
set ylabel "response time (ms)"

# Tell gnuplot to use tabs as the delimiter instead of spaces (default)
set datafile separator '\t'

# Plot the data
plot "result.plot" every ::2 using 5 title 'response time' with lines
exit
```

위의 `script`를 `gnuplot`으로 실행한다.

```bash
$ gnuplot script.plot
```

그러면 다음과 같은 그래프를 이미지로 얻어올 수 있다.

![img](https://blog.hkwon.me/content/images/2016/06/result.png)

request별 response time을 그래프로 확인해 볼 수 있는데, 다음과 같이 호출한 시간 별로 scatter chart처럼 그래프를 그려볼 수도 있다.

```bash
# 터미널 사이즈 조정(이미지 사이즈)
set terminal png size 1024,768

# 결과 파일 설정
set output "result.png"

# 범례/key 위치
set key left top

# y축 grid line
set grid y

# x축 데이터 지정
set xdata time

# input time format
set timefmt "%s"

# x축 time format 시:분:초
set format x "%H:%M:%S"

# Label the x-axis
set xlabel 'H:M:S'

# Label the y-axis
set ylabel "response time (ms)"

# 구분자로 탭을 사용
set datafile separator '\t'

# Plot the data
plot "result.plot" every ::2 using 2:5 title 'response time' with points

exit
```

위의 `script`를 `gnuplot`으로 다시 실행해 보면 시간순으로 point를 찍은 그래프를 확인할 수 있다.

![img](https://blog.hkwon.me/content/images/2016/06/result-2.png)



참고 : https://blog.hkwon.me/ab-apache-http-server-benchmarking-tool/