package io.simpolor.interceptor.interceptor;

import io.simpolor.interceptor.model.StudentDto;
import io.simpolor.interceptor.repository.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/***
 * preHandle : 컨트롤러 실행 직전에 동작
 * postHandle : 컨트롤러 진입 후 view 랜더링 되기전 수행
 * afterCompletion : 컨트롤러 집입 후 view가 정상적으로 랜더링 된 후 마지막으로 실행
 *
 * request에 값을 직접 주입할 수 없수 없으므로 값 변조를 시킬 수 없음을 주의해야한다.
 */
@Slf4j
@Component
public class StudentInterceptor implements HandlerInterceptor {

    private final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Interceptor > preHandle");

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);

        /*ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        String requestBody = IOUtils.toString(requestWrapper.getInputStream(), StandardCharsets.UTF_8);*/
        // log.info("requestBody : {}", requestBody);

        // HttpSession session = request.getSession();
        // Student student = (Student) session.getAttribute("Student");
        // session.setMaxInactiveInterval(30 * 60); // 세션 유지시간을 연장

        // if(ObjectUtils.isEmpty(student)){
        //    response.sendRedirect("/student/not");
        //    return false;
        // }

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

        log.info("Interceptor > postHandle");

        /*if(handler instanceof StudentDto){
            StudentDto studentDto = (StudentDto) handler;
            studentDto.setName(studentDto.getName() + "_changed");
        }*/

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {

        log.info("Interceptor > afterCompletion");

        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(LOG_ID);

        request.setAttribute(LOG_ID, uuid);
        log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);
    }

}
