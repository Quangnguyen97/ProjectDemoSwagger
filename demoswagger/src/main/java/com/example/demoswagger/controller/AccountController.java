package com.example.demoswagger.controller;

import com.example.demoswagger.account.Account;
import com.example.demoswagger.account.AccountDto;
import com.example.demoswagger.account.AccountServiceImpl;
import com.example.demoswagger.module.ResourceException;
import com.example.demoswagger.module.ResourceResponse;
import com.example.demoswagger.response.Response;
import com.example.demoswagger.response.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Tài khoản", description = "Demo test API")
@RestController
public class AccountController extends BaseController {

    private final AccountServiceImpl serviceImpl;

    public AccountController(ModelMapper modelMapper, AccountServiceImpl serviceImpl) {
        super(modelMapper);
        this.serviceImpl = serviceImpl;
    }

    @GetMapping("/User/{userId}/Account")
    public ResponseEntity<ResponseDto> getAllAccounts(@PathVariable(name = "userId") long userId) {
        try {
            List<Account> listObject = serviceImpl.getByUserId(userId)
                    .stream()
                    .map(post -> modelMapper.map(post, Account.class))
                    .toList();
            if (listObject.isEmpty()) {
                throw new ResourceException("List account " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            return ResponseEntity.status(HttpStatus.OK).body(handleSuccess(new ArrayList<>(listObject)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleExpectationFailed(e.getMessage()));
        }
    }

    @GetMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> getAccountByNumber(@PathVariable(name = "userId") long userId,
                                                          @PathVariable(name = "accountNumber") int accountNumber) {
        try {
            Account account = serviceImpl.getByUserIdAndAccountNumber(userId, accountNumber);
            if (account == null) {
                throw new ResourceException("Account " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            List<Object> listObject = new ArrayList<>();
            listObject.add(account);
            return ResponseEntity.status(HttpStatus.OK).body(handleSuccess(new ArrayList<>(listObject)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleExpectationFailed(e.getMessage()));
        }
    }

    @PostMapping("/User/{userId}/Account")
    public ResponseEntity<ResponseDto> saveAccount(@PathVariable(name = "userId") long userId,
                                                   @RequestBody @Valid AccountDto accountDto) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            Account account = serviceImpl.saveAccount(userId, modelMapper.map(accountDto, Account.class));
            if (account == null) {
                throw new ResourceException("Account created failed");
            }
            List<Object> listObject = new ArrayList<>();
            listObject.add(account);
            ResourceResponse.responseDto(responseDto, HttpStatus.CREATED.value(),
                    HttpStatus.CREATED.getReasonPhrase(), "Account created successfully", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @PutMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> updateAccount(@PathVariable(name = "userId") long userId,
                                                     @RequestBody @Valid AccountDto accountDto,
                                                     @PathVariable(name = "accountNumber") int accountNumber) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            Account account = serviceImpl.updateAccount(userId, modelMapper.map(accountDto, Account.class),
                    accountNumber);
            if (account == null) {
                throw new ResourceException("Account updated failed");
            }
            List<Object> listObject = new ArrayList<>();
            listObject.add(account);
            ResourceResponse.responseDto(responseDto, HttpStatus.ACCEPTED.value(),
                    HttpStatus.ACCEPTED.getReasonPhrase(), "Account updated successfully", listObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }

    @DeleteMapping("/User/{userId}/Account/{accountNumber}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable(name = "userId") long userId,
                                                     @PathVariable(name = "accountNumber") int accountNumber) {
        ResponseDto responseDto = modelMapper.map(Response.class, ResponseDto.class);
        try {
            if (serviceImpl.deleteAccount(userId, accountNumber)) {
                ResourceResponse.responseDto(responseDto, HttpStatus.ACCEPTED.value(),
                        HttpStatus.ACCEPTED.getReasonPhrase(), "Account deleted successfully", null);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
            } else {
                throw new ResourceException("Account deleted failed");
            }
        } catch (Exception e) {
            ResourceResponse.responseDto(responseDto, HttpStatus.EXPECTATION_FAILED.value(),
                    HttpStatus.EXPECTATION_FAILED.getReasonPhrase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseDto);
        }
    }
}