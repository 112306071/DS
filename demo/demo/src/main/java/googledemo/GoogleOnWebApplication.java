// package googledemo; <- 確保這個 package 路徑正確

package googledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleOnWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoogleOnWebApplication.class, args);
    }
}
