package com.example.blog._core.util.error;

import com.example.blog._core.util.error.ex.Exception400;
import com.example.blog._core.util.error.ex.Exception401;
import com.example.blog._core.util.error.ex.Exception403;
import com.example.blog._core.util.error.ex.Exception404;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        System.out.println("err400");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception403.class)
    public String err403(Exception403 e) {
        System.out.println("err403");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }


    @ResponseBody
    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e) {
        System.out.println("err404");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception401.class)
    public String err401(Exception401 e) {
        System.out.println("err401");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());

        return body;
    }

}
