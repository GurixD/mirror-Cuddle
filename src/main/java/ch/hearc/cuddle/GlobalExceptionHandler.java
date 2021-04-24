package ch.hearc.cuddle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, HttpServletRequest request,
                               HttpServletResponse response, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());

        return "redirect:"+request.getHeader("Referer");

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeError(MaxUploadSizeExceededException e, HttpServletRequest request,
                                           HttpServletResponse response, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "File size is too big (>8MB)");
        return "redirect:"+request.getHeader("Referer");

    }
}

