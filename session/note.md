# HttpSession 클래스를 사용하면?

get/setAttribute 로 서버에 세션을 저장할 수 있음

# 멀티 서버일 경우

세션을 사용하면 서버가 변경되었을 경우 session 정보가 없기 때문에 사용자 정보를 다시 요청해야하는 문제 발생

```text
1.
user request -> was <-> server 1 
                     -> server 2
                     -> server 3
                     
2.
                     -> server 1 
user request -> was <-> server 2 // session 없음, 재요청!
                     -> server 3
```

redis 를 사용해서 세션 클러스터링을 보통 진행함

```text
was -> redis -> server 1 
             -> server 2
             -> server 3
```

redis 운영 단점 보완 -> cookie 를 통한 인증