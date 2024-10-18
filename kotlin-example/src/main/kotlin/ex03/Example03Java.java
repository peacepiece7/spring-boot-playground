package ex03;

import java.util.ArrayList;
import java.util.Collections;

public class Example03Java {

    public static void main(String[] args) {
        var userList = new ArrayList<User>();

        var user = new User("foo", 111);
        var userListTemp = new ArrayList<User>();


        User[] userList2 = { new User("foo", 111), new User("bar", 222)};

        // 이런거 안됨
        //  var userMapList = {
        //    { name : "foo", age : 11},
        //    { name : "bar", age : 22}
        //   }

        // 자바에서 immutable 하려면?

        var immutableUserList = Collections.unmodifiableList(userList);
        // immutableUserList.add(new User("foo", 11)); 이런거 되긴함 컴파일시 에러는 터짐
    }

    public static class User {
        private String name;
        private int age;

        public User (String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

