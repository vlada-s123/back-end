package RestAssured;

import lombok.Builder;

@Builder
public class User {
   private String name;
   private String job;

   public String getName(){
       return name;
   }

    public String getJob() {
        return job;
    }
}
