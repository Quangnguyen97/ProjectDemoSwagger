package com.example.demoswagger.controller;

import com.example.demoswagger.account.Account;
import com.example.demoswagger.account.AccountDto;
import com.example.demoswagger.account.AccountServiceImpl;
import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Tài khoản", description = "Demo test API")
@RestController
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountServiceImpl serviceImpl;

    public AccountController(AccountServiceImpl serviceImpl) {
        super();
        this.serviceImpl = serviceImpl;
    }

    @GetMapping("/User/{userId}/Account")
    public ResponseEntity<ResponseDto> getAllAccounts(@PathVariable(name = "userId") long userId) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            List<Account> listAccount = serviceImpl.getByUserId(userId)
                    .stream()
                    .map(post -> modelMapper.map(post, Account.class))
                    .collect(Collectors.toList());
            if (listAccount.isEmpty()) {
                throw new ResourceException("List account " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            for (Account account : listAccount) {
                listObject.add(account);
            }
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @GetMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> getAccountByNumber(@PathVariable(name = "userId") long userId,
                                                          @PathVariable(name = "accountNumber") int accountNumber) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            Account account = serviceImpl.getByUserIdAndAccountNumber(userId, accountNumber);
            if (account == null) {
                throw new ResourceException("Account " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<Object>();
            listObject.add(account);
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(), "", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @PostMapping("/User/{userId}/Account")
    public ResponseEntity<ResponseDto> saveAccount(@PathVariable(name = "userId") long userId,
                                                   @RequestBody @Valid AccountDto accountDto) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            Account account = serviceImpl.saveAccount(userId, modelMapper.map(accountDto, Account.class));
            if (account == null) {
                throw new ResourceException("Account created failed");
            }
            List<Object> listObject = new ArrayList<Object>();
            listObject.add(account);
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.CREATED.value(),
                    HttpStatus.CREATED.getReasonPhrase(), "Account created successfully", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @PutMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> updateAccount(@PathVariable(name = "userId") long userId,
                                                     @RequestBody @Valid AccountDto accountDto, @PathVariable(name = "accountNumber") int accountNumber) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            Account account = serviceImpl.updateAccount(userId, modelMapper.map(accountDto, Account.class),
                    accountNumber);
            if (account == null) {
                throw new ResourceException("Account updated failed");
            }
            List<Object> listObject = new ArrayList<Object>();
            listObject.add(account);
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.ACCEPTED.value(),
                    HttpStatus.ACCEPTED.getReasonPhrase(), "Account updated successfully", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @DeleteMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable(name = "userId") long userId,
                                                     @PathVariable(name = "accountNumber") int accountNumber) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            if (serviceImpl.deleteAccount(userId, accountNumber)) {
                ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.ACCEPTED.value(),
                        HttpStatus.ACCEPTED.getReasonPhrase(), "Account deleted successfully", null);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseDto);
            } else {
                throw new ResourceException("Account deleted failed");
            }
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ResponseDto> HandleHttpMessageException(
            HttpMessageNotReadableException exception) {
        ResponseDto ResponseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        } catch (Exception e) {
            ResponseDto = ResourceResponse.ResponseDto(ResponseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto);
        }
    }
}
