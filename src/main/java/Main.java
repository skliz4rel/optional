import java.util.Locale;
import java.util.Optional;

public class Main {

    public static void main(String[] args){

        sampleusage1();
        sampleusage2();

    }


    public static void sampleusage1(){

        Optional<Object> empty =  Optional.empty(); //This is when u konw it empty
        System.out.println(empty.isPresent());
        System.out.println(empty.isEmpty());

        Optional<String> hello = Optional.of("Hello");

        Optional<String> empty1 = Optional.of("Hello"); //This is used when you sure there is a value. Optional.of is used when you sure there is a value. If you pass null you get a NullPointer exception
        System.out.println(empty1.isPresent());
        System.out.println(empty1.isEmpty());


        String orelse =  empty1.orElse("default");
        System.out.println(orelse);


        //So when you are not sure if it going to be nullable you use
        Optional<String> empty2 = Optional.ofNullable(null);
        System.out.println(empty2.isPresent());


        String orelse1 = empty2.map(item -> item.toUpperCase())
                .orElse("world");

        System.out.println(orelse1);

        orelse1 = empty2.map(item -> item.toUpperCase())
                .orElseGet(()->{//THis is signfy you are passing a supplier.

                    //You can perform some operations here like a db operation. Just incase it not present

                    return "World";
                });

        System.out.println(orelse1);

        orelse1 = empty2.map(item -> item.toUpperCase())
                .orElseThrow(IllegalStateException::new);

        System.out.println(orelse1);

        //perform a manipulation if persent

        hello.ifPresent(word->{
            System.out.println(word);
        });


        hello.ifPresentOrElse(word->{
            System.out.println(word);
        }, () ->{ //This is a runnable

            System.out.println("world");
        });

    }

    public static void sampleusage2(){

        Person person =  new Person("jide", null);

        String email = person.getEmail()
                .map(String::toUpperCase)
                .orElse("email not available");

        System.out.println(email);


        //This approach below is the same as what you have above
        if(person.getEmail().isPresent()){
            email = person.getEmail().get();
            System.out.println(email.toLowerCase());
        }
        else{
            System.out.println("email not provided");
        }
    }

   static class Person{
        private final String name;
        private final String email;

        Person(String name, String email){
            this.name = name;
            this.email = email;
        }

        public String getName(){

            return this.name;
        }

        public Optional<String> getEmail(){
            return Optional.ofNullable(email);
        }
    }
}