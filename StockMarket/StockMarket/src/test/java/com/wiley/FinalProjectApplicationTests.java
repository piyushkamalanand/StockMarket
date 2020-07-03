package com.wiley;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.wiley.dto.BuyOrderDTO;
import com.wiley.dto.LoginDTO;
import com.wiley.dto.SellOrderDTO;
import com.wiley.dto.SortDTO;
import com.wiley.repositories.BuyOrderRepository;
import com.wiley.repositories.SellOrderRepository;
import com.wiley.repositories.SortRepository;
import com.wiley.repositories.UserRepository;
import com.wiley.services.BuyOrderService;
import com.wiley.services.SellOrderService;
import com.wiley.services.SortService;
import com.wiley.services.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
class FinalProjectApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private BuyOrderService buyOrderService;
	@MockBean
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderService sellOrderService;
	@MockBean
	private SellOrderRepository sellOrderRepository;
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private SortService sortService;
	@MockBean
	private SortRepository sortRepository;
    @Test
    public void deleteRecordsTest()
    {
    	int userId=2;
    	when(userRepository.deleteUser(userId)).thenReturn(1);
    	assertEquals(1,userService.deleteRecords(2));
    	
    }
    @Test
    public void insertRecords()
    {
    	String uname="ravi";
    	String password="ravi";
    	String email="ravi@gmail.com";
    	when(userRepository.insertUser(uname,password,email)).thenReturn(1);
    	assertEquals(1,userService.insertRecords(uname, password, email));
    }
    @Test
    public void updateRecords()
    {
    	String email="ravi@gmail.com";
    	String password="raviiii";
    	when(userRepository.updateUser(email, password)).thenReturn(1);
    	assertEquals(1,userService.updateRecords(email, password));
    }
    @Test
    public void loadBuyOrders()
    {
    	when(buyOrderRepository.findAllBuyOrders()).thenReturn(Stream.of(new BuyOrderDTO(1,25,"apple",2000),new BuyOrderDTO(2,50,"google",1000)).collect(Collectors.toList()));
    	assertEquals(2,buyOrderService.loadBuyOrders().size());
    	
    }
    @Test
    public void insertBuyOrders()
    {
    	int orderId=4;
    	int noOfShares=50;
    	String companyName="techmahindra";
    	int sharePrice=500;
    	int orderBookID=1;
    	when(buyOrderRepository.addBuyOrders(orderId, noOfShares, companyName, sharePrice, orderBookID)).thenReturn(1);
    	assertEquals(1,buyOrderService.insertBuyOrders(orderId, noOfShares, companyName, sharePrice, orderBookID));
    }
    @Test
    public void deleteBuyOrder()
    {
    	int orderId=2;
    	when(buyOrderRepository.deleteBuyOrder(orderId)).thenReturn(1);
    	assertEquals(1,buyOrderService.deleteBuyOrders(orderId));
    }
    @Test
    public void loadSellOrders()
    {
    	when(sellOrderRepository.findAllSellOrders()).thenReturn(Stream.of(new SellOrderDTO(1,25,"apple",2000),new SellOrderDTO(2,50,"google",1000)).collect(Collectors.toList()));
    	assertEquals(2,sellOrderService.loadSellOrders().size());
    	
    }
    @Test
    public void insertSellOrders()
    {
    	int orderId=4;
    	int noOfShares=50;
    	String companyName="techmahindra";
    	int sharePrice=500;
    	int orderBookID=1;
    	when(sellOrderRepository.addSellorder(orderId, noOfShares, companyName, sharePrice, orderBookID)).thenReturn(1);
    	assertEquals(1,sellOrderService.insertSellOrders(orderId, noOfShares, companyName, sharePrice, orderBookID));
    }
    @Test
    public void deleteSellOrder()
    {
    	int orderId=2;
    	when(sellOrderRepository.deleteSellOrder(orderId)).thenReturn(1);
    	assertEquals(1,sellOrderService.deleteSellOrders(orderId));
    }
    
    @Test
    public void loginUser()
    {
    	String userName="ravi";
    	String password="ravi";
    	int userId=5;
    	when(userRepository.findAllUsers()).thenReturn(Stream.of(new LoginDTO("ravi","ravi",5),new LoginDTO("yuvraj","yuvraj",6)).collect(Collectors.toList()));
    	assertEquals(5,userService.loginUser(userName, password));
    }
  
   @Test
   public void executeBuyTrade()
   {
    	int userId=3;
    	int noOfShares=25;
    	String companyName="wiley";
    	int sharePrice=1000;
    	int[] res= {1,0};
    
    	when(sortRepository.findAllSellOrders()).thenReturn(Stream.of(new SortDTO(1,"wiley",25,1000),new SortDTO(2,"google",50,1500)).collect(Collectors.toList()));
    	assertArrayEquals(res,sortService.executeBuyTrade(userId,noOfShares, companyName, sharePrice));
    	
    	
    	
   }
   @Test
   public void executeSellTrade()
   {
    	int userId=3;
    	int noOfShares=25;
    	String companyName="wiley";
    	int sharePrice=1000;
    	int[] res= {1,0};
    
    	when(sortRepository.findAllBuyOrders()).thenReturn(Stream.of(new SortDTO(1,"wiley",25,1000),new SortDTO(2,"google",50,1500)).collect(Collectors.toList()));
    	assertArrayEquals(res,sortService.executeSellTrade(userId,noOfShares, companyName, sharePrice));
    	    	
   }
}