package ar.com.norrmann.coqui.jsf.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ViewExpiredExceptionExceptionHandlerFactory extends ExceptionHandlerFactory {
    private ExceptionHandlerFactory parent;

    public ViewExpiredExceptionExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ViewExpiredExceptionExceptionHandler(parent.getExceptionHandler());
    }
}