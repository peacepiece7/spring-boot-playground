package exam10_extend_function;

import java.util.Optional;

/**
 * isBlack, ofNullable, ifPresent 등 귀찮음
 */
public class Exam10 {

    public Exam10(ExamUser examUser){

        var optionalUser = Optional.ofNullable(examUser);
        optionalUser.ifPresent(it -> {
            Optional.ofNullable(examUser.getName()).ifPresent(name -> {

                if(!name.isBlank()){
                    System.out.println(name);
                }
            });

        });

        /**
         * 부정 연산자 뺴고 쓰는 방식, 공통으로 미리 만들어두고 씀
         */
        if(ObjectUtils.isNotNull(examUser) && ObjectUtils.isNotNull(examUser.getName())){
            if(StringUtils.notBlank(examUser.getName())){
                System.out.println(examUser.getName());
            }
        }
    }

    public static void main(String[] args){
        var user = new ExamUser();
        user.setName("abcd");

        new Exam10(user);
    }
}

class ObjectUtils {
    public static boolean isNotNull(Object obj){
        return obj != null;
    }
}

class StringUtils {
    public static boolean notBlank(String value){
        return !value.isBlank();
    }
}


class ExamUser{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
