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