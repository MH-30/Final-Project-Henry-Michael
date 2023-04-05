package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.Shirt;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.repository.ShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Optional;

@Component
public class ServiceLayer {

    /*
    Sales tax applies only to the cost of the items.

Sales tax does not apply to any processing fees for an invoice.

The processing fee is applied only once per order, regardless of the number of items in the order, unless the number of items in the order is greater than 10, in which case an additional processing fee of $15.49 is applied to the order.

The order-processing logic must properly update the quantity available for the item in the order.

Order quantity must be greater than zero.

Order quantity must be less than or equal to the number of items available in the inventory.

The order must contain a valid state code.

The REST API must properly handle and report all violations of business rules.
     */

    private ConsoleRepository consoleRepo;
    private InvoiceRepository invoiceRepo;
    private GameRepository gameRepo;
    private ShirtRepository shirtRepo;
    private HashMap<String, BigDecimal> taxMap;
    private HashMap<String, BigDecimal> feeMap;

    public ServiceLayer() {
        /*taxMap = new HashMap<String, Double>();
        feeMap = new HashMap<String, Double>();
        fillUpTaxes();
        fillUpFees();*/
    }

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepo, InvoiceRepository invoiceRepo,
                        GameRepository gameRepo, ShirtRepository shirtRepo) {
        this.consoleRepo = consoleRepo;
        this.invoiceRepo = invoiceRepo;
        this.gameRepo = gameRepo;
        this.shirtRepo = shirtRepo;
        this.taxMap = new HashMap<String, BigDecimal>();
        this.feeMap = new HashMap<String, BigDecimal>();
        fillUpTaxes();
        fillUpFees();
    }

    public void fillUpTaxes() {
        //10
        taxMap.put("AL", BigDecimal.valueOf(.05));
        taxMap.put("AK", BigDecimal.valueOf(.06));
        taxMap.put("AZ", BigDecimal.valueOf(.04));
        taxMap.put("AR", BigDecimal.valueOf(.06));
        taxMap.put("CA", BigDecimal.valueOf(.06));
        taxMap.put("CO", BigDecimal.valueOf(.04));
        taxMap.put("CT", BigDecimal.valueOf(.03));
        taxMap.put("DE", BigDecimal.valueOf(.05));
        taxMap.put("FL", BigDecimal.valueOf(.06));
        taxMap.put("GA", BigDecimal.valueOf(.07));
        //10
        taxMap.put("HI", BigDecimal.valueOf(.05));
        taxMap.put("ID", BigDecimal.valueOf(.03));
        taxMap.put("IL", BigDecimal.valueOf(.05));
        taxMap.put("IN", BigDecimal.valueOf(.05));
        taxMap.put("IA", BigDecimal.valueOf(.04));
        taxMap.put("KS", BigDecimal.valueOf(.06));
        taxMap.put("KY", BigDecimal.valueOf(.04));
        taxMap.put("LA", BigDecimal.valueOf(.05));
        taxMap.put("ME", BigDecimal.valueOf(.03));
        taxMap.put("MD", BigDecimal.valueOf(.07));
        //10
        taxMap.put("MA", BigDecimal.valueOf(.05));
        taxMap.put("MI", BigDecimal.valueOf(.06));
        taxMap.put("MN", BigDecimal.valueOf(.06));
        taxMap.put("MS", BigDecimal.valueOf(.05));
        taxMap.put("MO", BigDecimal.valueOf(.05));
        taxMap.put("MT", BigDecimal.valueOf(.03));
        taxMap.put("NE", BigDecimal.valueOf(.04));
        taxMap.put("NV", BigDecimal.valueOf(.04));
        taxMap.put("NH", BigDecimal.valueOf(.06));
        taxMap.put("NJ", BigDecimal.valueOf(.05));
        //10
        taxMap.put("NM", BigDecimal.valueOf(.05));
        taxMap.put("NY", BigDecimal.valueOf(.06));
        taxMap.put("NC", BigDecimal.valueOf(.05));
        taxMap.put("ND", BigDecimal.valueOf(.05));
        taxMap.put("OH", BigDecimal.valueOf(.04));
        taxMap.put("OK", BigDecimal.valueOf(.04));
        taxMap.put("OR", BigDecimal.valueOf(.07));
        taxMap.put("PA", BigDecimal.valueOf(.06));
        taxMap.put("RI", BigDecimal.valueOf(.06));
        taxMap.put("SC", BigDecimal.valueOf(.06));
        //10
        taxMap.put("SD", BigDecimal.valueOf(.06));
        taxMap.put("TN", BigDecimal.valueOf(.05));
        taxMap.put("TX", BigDecimal.valueOf(.03));
        taxMap.put("UT", BigDecimal.valueOf(.04));
        taxMap.put("VT", BigDecimal.valueOf(.07));
        taxMap.put("VA", BigDecimal.valueOf(.06));
        taxMap.put("WA", BigDecimal.valueOf(.05));
        taxMap.put("WV", BigDecimal.valueOf(.05));
        taxMap.put("WI", BigDecimal.valueOf(.03));
        taxMap.put("WY", BigDecimal.valueOf(.04));

    }

    public void fillUpFees() {
        feeMap.put("console", BigDecimal.valueOf(14.99));
        feeMap.put("tshirt", BigDecimal.valueOf(1.98));
        feeMap.put("game", BigDecimal.valueOf(1.49));
    }

    @Transactional
    public Invoice save(Invoice invoice) throws IllegalArgumentException {
        Optional<Invoice> selectedInvoice = invoiceRepo.findById(invoice.getInvoiceId());

        if (!selectedInvoice.isPresent()) throw new IllegalArgumentException();

        String stateKey = invoice.getState();
        if (!this.taxMap.containsKey(stateKey)) throw new IllegalArgumentException();
        if (!invoice.getItemType().equals("console") &&
                !invoice.getItemType().equals("tshirt") &&
                !invoice.getItemType().equals("game")) throw new IllegalArgumentException();

        Integer quanityOfItems = 0;
        BigDecimal priceOfItem = BigDecimal.valueOf(0);
        if (invoice.getItemType().equals("console")) {
            Optional<Console> consoleOptional = consoleRepo.findById(invoice.getItemId());
            if (consoleOptional.isPresent()) throw new IllegalArgumentException();
            quanityOfItems = consoleOptional.get().getQuantity();
            priceOfItem = BigDecimal.valueOf(consoleOptional.get().getPrice());
        }
        else if (invoice.getItemType().equals("tshirt")) {
            Optional<Shirt> shirtOptional = shirtRepo.findById(invoice.getItemId());
            if (shirtOptional.isPresent()) throw new IllegalArgumentException();
            quanityOfItems = shirtOptional.get().getQuantity();
            priceOfItem = BigDecimal.valueOf(shirtOptional.get().getPrice());
        }
        else {
            Optional<Game> gameOptional = gameRepo.findById(invoice.getItemId());
            if (gameOptional.isPresent()) throw new IllegalArgumentException();
            quanityOfItems = gameOptional.get().getQuantity();
            priceOfItem = BigDecimal.valueOf(gameOptional.get().getPrice());
        }

        if (quanityOfItems > invoice.getQuantity() || quanityOfItems <= 0) throw new IllegalArgumentException();
        BigDecimal processingFee = BigDecimal.valueOf(0);

        if (quanityOfItems > 10) processingFee = BigDecimal.valueOf(15.49);
        processingFee.add(feeMap.get(stateKey)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal subtotal = priceOfItem.multiply(new BigDecimal(quanityOfItems)).setScale(2, RoundingMode.HALF_UP);


        BigDecimal salesTax = taxMap.get(stateKey).multiply(subtotal).setScale(2, RoundingMode.HALF_UP);

        invoice.setTax(salesTax);
        invoice.setProcessingFee(processingFee);
        invoice.setSubtotal(subtotal);
        BigDecimal totalSum = processingFee.add(subtotal).add(salesTax);

        invoice.setTotal(totalSum);
        invoice.setUnitPrice(priceOfItem);
        invoice.setName("Tim");
        invoice.setZipcode("33331");
        invoice.setStreet("12345");
        invoice.setCity("Davis");
        invoice.setQuantity(quanityOfItems);
        invoiceRepo.save(invoice);
        return invoice;
    }


}
