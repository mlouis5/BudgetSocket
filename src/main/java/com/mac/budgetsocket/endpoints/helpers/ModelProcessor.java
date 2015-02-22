/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.endpoints.helpers;

import com.mac.budgetentities.Addressable;
import com.mac.budgetentities.IdGenerator;
import com.mac.budgetentities.pojos.Address;
import com.mac.budgetentities.pojos.Bill;
import com.mac.budgetentities.pojos.User;
import com.mac.budgetsocket.pojos.ui.AddressUIModel;
import com.mac.abstractrepository.budgetrepo.AddressDao;
import com.mac.abstractrepository.budgetrepo.BillDao;
import com.mac.abstractrepository.budgetrepo.UserDao;
import com.mac.budgetsocket.pojos.ui.BillUIModel;
import com.mac.budgetsocket.pojos.ui.BudgetUIModel;
import com.mac.budgetsocket.pojos.ui.Console;
import com.mac.budgetsocket.pojos.ui.UserUIModel;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class ModelProcessor implements ApplicationContextAware {

    private ApplicationContext ctx;
    private UserDao ud;
    private BillDao bd;
    private AddressDao ad;

    public Console processModel(BudgetUIModel bm) {
        Console console = new Console();        
        console.setFailedCount(-1);
        console.setSuccessCount(-1);

        if (Objects.nonNull(bm)) {
            if (Objects.nonNull(ctx)) {                
                User user = makeUserFromContainer(bm.getUser());

                if (isIdViable(user)) {                    
                    List<BillUIModel> bills = bm.getBills();
                    Address userAddr = makeAddressFromModel(bm.getUser().getAddress());
                    if (Objects.nonNull(bills) && !bills.isEmpty()) {                        
                        int totalBills = bills.size();
                        int successCount = 0;
                        for (BillUIModel billModel : bills) {                            
                            boolean handled;
                            Address billAddr = makeAddressFromModel(billModel.getAddress());

                            Bill bill = makeBillFromContainer(billModel);
                            
                            if (Objects.nonNull(bill)) {
                                user = setOwner(user, userAddr);
                                handled = setBill(bill, user, billAddr);
                                successCount += handled ? 1 : 0;
                            }
                        }
                        console.setMessage("Processed bills, see bill counts");
                        console.setSuccessCount(successCount);
                        console.setFailedCount(totalBills - successCount);
                    } else {
                        user = setOwner(user, userAddr);                        
                        console.setMessage((Objects.nonNull(user) ? "Added User, unable to add bills" : "Unable to add user or bills"));
                    }
                }else{
                    console.setMessage("Invalid user, correct and retry");
                }
            }else{
                console.setMessage("Internal Error");
            }
        }else{
            console.setMessage("Model was null");
        }        
        return console;
    }

    private User verifyUser(User user) {
        return ud.find(user.getGeneratedId());
    }

    private void addUser(User user) {
        ud.create(user);
    }

    private Bill verifyBill(Bill bill) {
        return bd.find(bill.getGeneratedId());
    }

    private void addBill(Bill bill) {
        bd.create(bill);
    }

    private Address verifyAddress(Address address) {
        return ad.find(address.getGeneratedId());
    }

    private void addAddress(Address address) {
        ad.create(address);
    }

    private User makeUserFromContainer(UserUIModel um) {
        User user = null;
        if (Objects.nonNull(um)) {
            user = new User();
            user.setUserFname(um.getfName());
            user.setUserLname(um.getlName());
            user.setUserPhone(um.getPhone());
            user.setUserEmail(um.getEmail());
            user.setUserPreferredContact(um.getPreference());
            user.generateId();
        }
        return user;
    }

    private Bill makeBillFromContainer(BillUIModel bm) {
        Bill bill = null;
        if (Objects.nonNull(bm)) {
            bill = new Bill();
            bill.setBillAmount(bm.getAmount());
            bill.setBillDueDate(bm.getDueDate());
            bill.setBillGracePeriod(bm.getGracePeriod());
            bill.setBillInterestRate(bm.getInterestRate());
            bill.setBillIsRevolving(bm.isIsRevolving());
            bill.setBillLateFeeAmount(bm.getLateFee());
            bill.setBillName(bm.getBillName());
            bill.setBillNumPayments(bm.getRemainingPmt());
            bill.setBillSource(bm.getSource());
            bill.setBillType(bm.getType());
            bill.setBillWebsite(bm.getWebsite());
        }
        return bill;
    }

    private Address makeAddressFromModel(AddressUIModel ac) {
        Address address = null;
        if (Objects.nonNull(ac)) {
            address = new Address();
            address.setAddressLine1(ac.getAddLine1());
            address.setAddressLine2(ac.getAddLine2());
            address.setAddressCity(ac.getCity());
            address.setAddressState(ac.getState());
            address.setAddressZipcode(ac.getZip());
            address.generateId();
        }
        return address;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ctx = ac;
        if (Objects.nonNull(ctx)) {
            ud = ctx.getBean(UserDao.class);
            bd = ctx.getBean(BillDao.class);
            ad = ctx.getBean(AddressDao.class);
        }
    }

    private boolean isAddressViable(Address addr) {
        return Objects.nonNull(addr) && Objects.nonNull(addr.getAddressId())
                && !addr.getAddressId().isEmpty();
    }

    private boolean isIdViable(IdGenerator gen) {
        if (Objects.isNull(gen)) {
            return false;
        }
        return Objects.nonNull(gen.getGeneratedId()) && !gen.getGeneratedId().isEmpty();
    }

    private void setAddress(Addressable addressable, Address address) {
        if (isAddressViable(address)) {
            Address verifiedAddress = verifyAddress(address);

            if (Objects.nonNull(verifiedAddress)) {
                address = verifiedAddress;
            } else {
                addAddress(address);
            }
            addressable.setAddress(address);
        }
    }

    private User setOwner(User user, Address userAddr) {
        User verifiedUser = verifyUser(user);
        verifiedUser.print();
        if (Objects.nonNull(verifiedUser)) {
            user = verifiedUser;
        } else {
            if(Objects.nonNull(userAddr)){
                setAddress(user, userAddr);
            }
            
            addUser(user);
            
        }
        return user;
    }

    private boolean setBill(Bill bill, User user, Address billAddr) {
        bill.setBillOwner(user);
        bill.generateId();
        Bill verifiedBill = verifyBill(bill);
        if (Objects.nonNull(verifiedBill)) {
            return false;
        }
        setAddress(bill, billAddr);
        addBill(bill);
        return true;
    }
}
