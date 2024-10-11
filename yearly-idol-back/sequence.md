# Use case

## 사용자

### 사용자 요구 기능

```mermaid
classDiagram
    class User {
        +String email
        +String userName
        +String profileImg
        +login() void
        +logout() void
%%      +editProfile(User) User
    }
```

### 로그인

- SNS 로그인 생각 있음
- password 암호화 나중에 할거임

```mermaid
flowchart LR
    SignIn[로그인]
    SignIn  --> SignIn1{로그인 시도}
        SignIn1 -.실패.-> SignUp(회원 가입)
        SignIn1 -.성공.-> Main(메인화면 이동)
```

### 회원 가입

```mermaid
flowchart LR
    SignUp(회원가입시도)
    SignUp --> SignUp1{email \n중복}
    SignUp1 -.성공.-> SignUp2{pw 유효성}
    SignUp2 -.성공.-> Main(로그인화면 이동)
```

[//]: # (### 사용자 정보 수정)

[//]: # ()
[//]: # (```mermaid)

[//]: # (flowchart LR)

[//]: # (    MyPage&#40;나의 정보 페이지&#41;)

[//]: # (    MyPage --> Edit1&#40;이름 변경&#41;)

[//]: # (    MyPage --> Edit2&#40;프로필 변경&#41;)

[//]: # (    MyPage --> Edit3&#40;비밀번호 변경&#41;)

[//]: # (```)

## 스케줄

### 스케줄 요구 기능

```mermaid
classDiagram
    class Scheduler {
        int scheduler_id
        String title
        String content
        String imgUrl
        +add_schedule()
        +remove_schedule()
        +edit_schedule()
        +findBy(Pageable pageable) Scheduler[]
        +findAll()
    }
```
