### 2. 使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例



#### 串行GC - c20 - N 60

```sh
> cd E:\geektime_JavaTrainingCamp\data\week1\jvm
> D:\Java\jdk1.8.0_131\bin\java -jar -XX:+UseSerialGC -XX:+PrintGCDetails -Xloggc:gc.ser.log -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar
> sb -u http://localhost:8088/api/hello -c 20 -N 60
```

```log
Starting at 2021/9/26 15:25:14
[Press C to stop the test]
284476  (RPS: 4244.3)
---------------Finished!----------------
Finished at 2021/9/26 15:26:21 (took 00:01:07.1119710)
Status 200:    284476

RPS: 4653.5 (requests/second)
Max: 550ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 2ms
  99%   below 3ms
99.9%   below 10ms
```

```
Starting at 2021/9/26 15:30:19
[Press C to stop the test]
291562  (RPS: 4508.7)
---------------Finished!----------------
Finished at 2021/9/26 15:31:24 (took 00:01:04.8663261)
Status 200:    291564

RPS: 4763.7 (requests/second)
Max: 81ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 2ms
  99%   below 3ms
99.9%   below 9ms
```



#### 串行GC - c40 - N 60

```sh
> sb -u http://localhost:8088/api/hello -c 40 -N 60
```

```
Starting at 2021/9/26 15:34:29
[Press C to stop the test]
226239  (RPS: 3226.9)
---------------Finished!----------------
Finished at 2021/9/26 15:35:39 (took 00:01:10.2460057)
Status 200:    226242

RPS: 3698.8 (requests/second)
Max: 654ms
Min: 0ms
Avg: 0.6ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 2ms
  98%   below 7ms
  99%   below 10ms
99.9%   below 36ms
```



#### 并行GC - c20 - N 60

```sh
> D:\Java\jdk1.8.0_131\bin\java -jar -XX:+UseParallelGC -XX:+PrintGCDetails -Xloggc:gc.par.log -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar
> sb -u http://localhost:8088/api/hello -c 20 -N 60
```

```
Starting at 2021/9/26 15:39:00
[Press C to stop the test]
304688  (RPS: 4688.2)
---------------Finished!----------------
Finished at 2021/9/26 15:40:05 (took 00:01:05.0216095)
Status 200:    304700

RPS: 4990.1 (requests/second)
Max: 208ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 2ms
  99%   below 3ms
99.9%   below 7ms
```



#### 并行GC - c40 - N 60

```sh
> sb -u http://localhost:8088/api/hello -c 40 -N 60
```

```
Starting at 2021/9/26 15:40:42
[Press C to stop the test]

15      (RPS: 3.2)                      ---------------Finished!----------------
3       (RPS: 0.6)                      Finished at 2021/9/26 15:40:47 (took 00:00:04.7414003)
34      (RPS: 7.2)                      Status 200:    34

RPS: 32.9 (requests/second)
Max: 63ms
Min: 15ms
Avg: 46.4ms

  50%   below 49ms
  60%   below 51ms
  70%   below 52ms
  80%   below 59ms
  90%   below 62ms
  95%   below 63ms
  98%   below 63ms
  99%   below 63ms
99.9%   below 63ms
```





#### CMS GC - c20 - N 60

```sh
> D:\Java\jdk1.8.0_131\bin\java -jar -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xloggc:gc.cms.log -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar
> sb -u http://localhost:8088/api/hello -c 20 -N 60
```

```txt
Starting at 2021/9/26 16:01:11
[Press C to stop the test]
286108  (RPS: 4351.1)
---------------Finished!----------------
Finished at 2021/9/26 16:02:17 (took 00:01:05.9671293)
Status 200:    286115

RPS: 4672 (requests/second)
Max: 1434ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 3ms
99.9%   below 8ms
```





#### CMS GC - c30 - N 60

```sh
> sb -u http://localhost:8088/api/hello -c 30 -N 60
```

```
Starting at 2021/9/26 16:03:33
[Press C to stop the test]

---------------Finished!----------------
Finished at 2021/9/26 16:03:38 (took 00:00:04.8059797)

PS C:\Users\Kayla Jo> sb -u http://localhost:8088/api/hello -c 30 -N 60
Starting at 2021/9/26 16:04:03
[Press C to stop the test]
263782  (RPS: 4073.8)
---------------Finished!----------------
Finished at 2021/9/26 16:05:08 (took 00:01:04.9216348)
Status 200:    263802

RPS: 4312.2 (requests/second)
Max: 1246ms
Min: 0ms
Avg: 0.2ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 1ms
  98%   below 3ms
  99%   below 5ms
99.9%   below 13ms
```



#### CMS GC - c40 - N 60

```sh
> sb -u http://localhost:8088/api/hello -c 40 -N 60
```

```
PS C:\Users\Kayla Jo> sb -u http://localhost:8088/api/hello -c 40 -N 60
Starting at 2021/9/26 16:05:56
[Press C to stop the test]

16      (RPS: 3.4)                      ---------------Finished!----------------
4       (RPS: 0.9)                      Finished at 2021/9/26 16:06:00 (took 00:00:04.7194279)
Status 200:    18

RPS: 17.4 (requests/second)
Max: 92ms
Min: 14ms
Avg: 66.9ms

  50%   below 76ms
  60%   below 77ms
  70%   below 79ms
  80%   below 81ms
  90%   below 90ms
  95%   below 92ms
  98%   below 92ms
  99%   below 92ms
99.9%   below 92ms
```



#### G1 GC - c20 - N 60

```sh
> D:\Java\jdk1.8.0_131\bin\java -jar -XX:+UseG1GC -XX:+PrintGCDetails -Xloggc:gc.g1.log -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar
> sb -u http://localhost:8088/api/hello -c 20 -N 60
```

```
Starting at 2021/9/26 16:08:07
[Press C to stop the test]
284361  (RPS: 4373.6)
---------------Finished!----------------
Finished at 2021/9/26 16:09:12 (took 00:01:05.1941644)
Status 200:    284363

RPS: 4648.3 (requests/second)
Max: 258ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 2ms
  99%   below 3ms
99.9%   below 11ms
```





#### G1 GC - c40 - N 60

```sh
> sb -u http://localhost:8088/api/hello -c 40 -N 60
```

```
Starting at 2021/9/26 16:10:02
[Press C to stop the test]
224690  (RPS: 3439.2)
---------------Finished!----------------
Finished at 2021/9/26 16:11:08 (took 00:01:05.4611839)
Status 200:    224699

RPS: 3675.1 (requests/second)
Max: 269ms
Min: 0ms
Avg: 0.5ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 3ms
  98%   below 7ms
  99%   below 10ms
99.9%   below 21ms
```


