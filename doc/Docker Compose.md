# Docker Compose



Docker Compose는 여러 개의 컨테이너(container)로 구성된 애플리케이션을 관리하기 위한 간단한 오케스트레이션(Orchestration) 도구입니다. 



## -f 옵션

Docker Compose는 기본적으로 커맨드가 실행하는 디렉토리에 있는 `docker-compose.yml` 또는 `docker-compose.yaml`를 설정 파일로 사용합니다. 다른 이름이나 경로의 파일을 Docker Compose 설정 파일로 사용하고 싶다면 `-f` 옵션으로 명시를 해줍니다.

```bash
$ docker-compose -f docker-compose-local.yml up
```

`-f` 옵션은 여러 개의 설정 파일을 사용할 때도 사용할 수 있습니다. 이 때는 나중에 나오는 설정이 앞에 나오는 설정보다 우선하게 됩니다.

```bash
$ docker-compose -f docker-compose.yml -f docker-compose-test.yml up
```

## up

`docker-compose up` 커맨드는 아마도 Docker Compose에서 가장 자주 사용되는 커맨드일 텐데요. Docker Compose에 정의되어 있는 모든 서비스 컨테이너를 한 번에 생성하고 실행하기 위해서 사용합니다.

보통 `-d` 옵션을 사용하여 백그라운드에서 컨테이너를 띄우는 경우가 많습니다.

```bash
$ docker-compose up -d
Creating network "django-app_default" with the default driver
Creating django-app_db_1 ... done
Creating django-app_web_1 ... done
$
```

`-d` 옵션을 사용하지 않으면 현재 터미널이 컨테이너의 로그가 출력되고 `Ctrl + C`를 눌러서 탈출하는 순간 컨테이너가 모두 정지되기 때문입니다.

## down

`docker-compose down` 커맨드는 `docker-compose up` 커맨드와 정반대의 동작을 합니다. Docker Compose에 정의되어 있는 모든 서비스 컨테이너를 한 번에 정지시키고 삭제합니다.

```bash
$ docker-compose down
Stopping django-app_web_1 ... done
Stopping django-app_db_1  ... done
Removing django-app_web_1 ... done
Removing django-app_db_1  ... done
Removing network django-app_default
```

## start

`docker-compose start` 커맨드는 내려가 있는 있는 특정 서비스 컨테이너를 올리기 위해서 사용합니다. 대부분의 경우에는 `docker-compose up` 커맨드를 사용해도 내려간 서비스를 알아서 올려주므로 무방합니다.

```bash
$ docker-compose start web
Starting web ... done
```

## stop

`docker-compose stop` 커맨드는 `docker-compose start` 커맨드와 정반대의 동작을 합니다. 돌아기고 있는 특정 서비스 컨테이너를 정지시키기 위해서 사용합니다.

```bash
$ docker-compose stop web
Stopping django-app_web_1 ... done
```

## ps

`docker-compose ps` 커맨드는 Docker Compose에 정의되어 있는 모든 서비스 컨테이너 목록을 조회할 때 사용합니다.

```bash
$ docker-compose ps
      Name                    Command               State           Ports
----------------------------------------------------------------------------------
django-app_db_1    docker-entrypoint.sh postgres    Up      5432/tcp
django-app_web_1   python manage.py runserver ...   Up      0.0.0.0:8000->8000/tcp
```

## logs

`docker-compose logs` 커맨드는 서비스 컨테이너의 로그를 확인하고 싶을 때 사용하며, 보통 `-f` 옵션을 붙여서 실시간 로그를 확인합니다.

```bash
$ docker-compose logs -f web
Attaching to django-app_web_1
web_1  | Watching for file changes with StatReloader
web_1  | Performing system checks...
web_1  |
web_1  | System check identified no issues (0 silenced).
web_1  |
web_1  | May 30, 2020 - 22:16:29
web_1  | Django version 3.0.6, using settings 'our_project.settings'
web_1  | Starting development server at http://0:8000/
web_1  | Quit the server with CONTROL-C.
```

## exec

`docker-compose exec` 커맨드는 실행 중인 서비스 컨테이너를 대상으로 어떤 명령어를 날릴 때 사용합니다.

```bash
$ docker-compose exec db psql postgres postgres
psql (12.2 (Debian 12.2-2.pgdg100+1))
Type "help" for help.

postgres=#
```

## run

`docker-compose run` 커맨드는 서비스 컨테이너의 특정 명령어를 일회성으로 실행할 때 사용합니다.

```bash
$ docker-compose run web env
PATH=/usr/local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
HOSTNAME=10757eb6642e
TERM=xterm
LANG=C.UTF-8
GPG_KEY=E3FF2839C048B25C084DEBE9B26995E310250568
PYTHON_VERSION=3.8.2
PYTHON_PIP_VERSION=20.1
PYTHON_GET_PIP_URL=https://github.com/pypa/get-pip/raw/1fe530e9e3d800be94e04f6428460fc4fb94f5a9/get-pip.py
PYTHON_GET_PIP_SHA256=ce486cddac44e99496a702aa5c06c5028414ef48fdfd5242cd2fe559b13d4348
PYTHONUNBUFFERED=1
HOME=/root
```

## config

`docker-compose config` 커맨드는 Docker Compose 설정을 확인할 때 사용합니다. `-f` 옵션으로 여러 개의 설정 파일을 사용할 때, 최종적으로 어떻게 설정이 적용되는지 확인해볼 때 유용합니다.

```bash
docker-compose config
services:
  db:
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_USER: postgres
    image: postgres
  web:
    build:
      context: /Users/dale/temp/django-app
    command: python manage.py runserver 0:8000
    depends_on:
    - db
    ports:
    - 8000:8000/tcp
    volumes:
    - /Users/dale/temp/django-app:/web:rw
version: '3.0'
```

## 

참고 : https://www.daleseo.com/docker-compose/