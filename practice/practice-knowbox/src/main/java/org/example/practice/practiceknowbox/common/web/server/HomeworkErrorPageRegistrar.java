package org.example.practice.practiceknowbox.common.web.server;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
public class HomeworkErrorPageRegistrar implements ErrorPageRegistrar {

    /* (non-Javadoc)
     * @see org.springframework.boot.web.server.ErrorPageRegistrar#registerErrorPages(org.springframework.boot.web.server.ErrorPageRegistry)
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

        errorPageRegistry.addErrorPages(page404, page500);
    }

}
