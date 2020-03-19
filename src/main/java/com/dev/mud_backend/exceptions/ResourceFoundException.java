package com.dev.mud_backend.exceptions;

import com.dev.mud_backend.controllers.UserController;
import com.dev.mud_backend.models.User;
import com.dev.mud_backend.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ResourceFoundException(String message)
    {
        super(message);
    }

    public ResourceFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

