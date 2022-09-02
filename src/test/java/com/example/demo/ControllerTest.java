package com.example.demo;

import com.example.demo.Models.Account.Checking;
import com.example.demo.Models.Account.Dto.AccountDto;
import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.Address;
import com.example.demo.Models.User.Admin;
import com.example.demo.Models.User.Dto.UserDto;
import com.example.demo.Models.User.User;
import com.example.demo.Repositories.Account.AccountRepository;
import com.example.demo.Repositories.Account.CheckingRepository;
import com.example.demo.Repositories.User.AccountHolderRepository;
import com.example.demo.Repositories.User.AdminRepository;
import com.example.demo.Repositories.User.RoleRepository;
import com.example.demo.Repositories.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest

public class ControllerTest {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    Gson gson = gsonBuilder.setPrettyPrinting().create();

    private final ObjectMapper objectMapper = new ObjectMapper();




    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
        adminRepository.save(new Admin("Paula", "1234"));
        AccountHolder accountHolder = accountHolderRepository.save(new AccountHolder("Joana","1234", null, null, LocalDate.of(1990,10, 05),new Address(),null));
        checkingRepository.save(new Checking(BigDecimal.valueOf(100),accountHolder,null,null,null,null,null));

    }
/*
  @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        accountRepository.deleteAll();
    }

*/


    @Test
    void createAccountHolder_test()throws Exception {

        UserDto accountHolder = new UserDto("Fabiola", "3456",null, null,LocalDate.now(),null,null,"");
        String body = gson.toJson(accountHolder);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/new-accountHolder")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Fabiola"));
    }

    @Test
    void createThirdPartUser_test()throws Exception {

        UserDto thirdPartUser = new UserDto("Fabiola", "3456",null, null,LocalDate.now(),null,null,"");
        String body = gson.toJson(thirdPartUser);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/new-thirdPartUser")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Fabiola"));
    }

    @Test
    void createAdmin_test()throws Exception {

        UserDto admin = new UserDto("Fabiola", "3456",null, null,LocalDate.now(),null,null,"");
        String body = gson.toJson(admin);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/new-admin")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Fabiola"));
    }

    @Test
    void createAccount_test()throws Exception {

        AccountDto account = new AccountDto(BigDecimal.valueOf(20000),2L,null,BigDecimal.valueOf(40),BigDecimal.valueOf(10000),BigDecimal.valueOf(0.5),LocalDate.now(),null,1234L,null,null, null);
        String body = gson.toJson(account);
        System.out.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/add-account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1234"));
    }

    //TODO como se hace?
    /*
    @Test
    public void modifyPassword_test()throws Exception{
        MvcResult result = mockMvc.perform(patch("/modify-password/?password=12345")).andExpect(status().isOk()).andReturn();
        assertTrue("30000".contains(result.getResponse().getContentAsString()));

    }

     */

    @Test
    public void transferMoney_test() throws Exception{
        MvcResult result = mockMvc.perform(post("/transfer-send/1?accountId=1?amount=2000?accountSecretKey=1234"))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(result.getResponse().getContentAsString().isBlank());
    }

    /* Como hago los de transferencia?
    @Test
    public void setBalance_test()throws Exception{
     MvcResult result = mockMvc.perform(patch("/set-balance/1?balance=30000")).andExpect(status().isOk()).andReturn();
    assertTrue("30000".contains(result.getResponse().getContentAsString()));

    }
*/
}
/*
ProductDTO product = new ProductDTO("New product", 69, 1L);
        String body = gson.toJson(product);
        MvcResult mvcResult = mockMvc.perform(post("/new-product")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("New product"));
 */