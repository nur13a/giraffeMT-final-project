package itAcademy.giraffeMT.giraffeMT.services.impl;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.actions.Transaction;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.models.actions.TransactionModel;
import com.company.banksystem.service.interfaces.BankAccountService;
import com.company.banksystem.service.interfaces.TransactionService;
import itAcademy.giraffeMT.giraffeMT.entities.Item;
import itAcademy.giraffeMT.giraffeMT.entities.Purchase;
import itAcademy.giraffeMT.giraffeMT.entities.User;
import itAcademy.giraffeMT.giraffeMT.enums.PaymentType;
import itAcademy.giraffeMT.giraffeMT.enums.Status;
import itAcademy.giraffeMT.giraffeMT.exceptions.NotFound;
import itAcademy.giraffeMT.giraffeMT.dto.PurchaseDto;
import itAcademy.giraffeMT.giraffeMT.repositories.PurchaseRepository;
import itAcademy.giraffeMT.giraffeMT.services.ItemService;
import itAcademy.giraffeMT.giraffeMT.services.PurchaseService;
import itAcademy.giraffeMT.giraffeMT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private TransactionService transactionService;

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getById(Long id) throws Exception {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElse(null);
    }

    @Override
    public Purchase create(PurchaseDto model) throws NotFound {
        return null;
    }

    @Override
    public PurchaseDto createByModel(PurchaseDto model) throws NotFound {
        try {
            User userFrom = userService.findByLogin(model.getUserFrom());
            User userTo = userService.findByLogin(model.getUserTo());
            Item item = itemService.getById(model.getItemId());
            Purchase purchase = Purchase.builder().userFrom(userFrom)
                    .item(item)
                    .userTo(userTo).build();
            if (model.getPaymentType().equals(PaymentType.BANK_ACCOUNT)) {
                BankAccount accountTo = userTo.getBankAccount();
                BankAccount accountFrom = userFrom.getBankAccount();
                TransactionModel transaction = TransactionModel.builder().accountFrom(accountFrom)
                        .accountTo(accountTo)
                        .amount(item.getPrice())
                        .currency(item.getCurrency()).build();
                Transaction transaction1 = transactionService.create(transaction);
                  if (transaction1.getStatus().equals(TransactionStatus.OK)) {
                itemService.update(item);
                purchaseRepository.save(purchase);
                  }
            } else if(model.getPaymentType().equals(PaymentType.IN_CASH)){
                item.setStatus(Status.SALES);
                itemService.update(item);
                purchaseRepository.save(purchase);
            }
            return  PurchaseDto.builder().userFrom(model.getUserFrom())
                    .itemId(model.getItemId())
                    .userTo(model.getUserTo()).paymentType(model.getPaymentType()).build();
        } catch (Exception e) {
            throw new NotFound("User or item does not exist");
        }
    }

    /*private Transaction doTransaction(Transaction transaction){
            if(transaction.getStatus().equals(TransactionStatus.OK)){
                transactionService.
            }
    }*/
    @Override
    public void delete(Long id) throws Exception {
        Purchase purchase = getById(id);
        if (purchase != null)
            purchaseRepository.delete(purchase);
        throw new NotFound("Purchase doesn't exist");
    }

    @Override
    public Purchase update(Purchase entity) {
        return purchaseRepository.save(entity);
    }
}
