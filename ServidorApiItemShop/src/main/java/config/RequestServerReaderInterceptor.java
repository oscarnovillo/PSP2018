/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider
public class RequestServerReaderInterceptor implements ReaderInterceptor,WriterInterceptor {
 
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) 
      throws IOException, WebApplicationException {
        InputStream is = context.getInputStream();
        String body = new BufferedReader(new InputStreamReader(is)).lines()
          .collect(Collectors.joining("\n"));
 
        context.setInputStream(new ByteArrayInputStream(
          (body + " message added in server reader interceptor").getBytes()));
 
        return context.proceed();
    }

   @Override
    public void aroundWriteTo(WriterInterceptorContext context) 
      throws IOException, WebApplicationException {
        
        context.getOutputStream()
          .write(("Message added in the writer interceptor in the client side").getBytes());
 
        context.proceed();
    }


}