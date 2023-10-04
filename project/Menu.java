import java.util.*;

public class Menu
{  
    public static double seconds = 2.5;
    
    
    public int showOffersOptions(Donator donator)
    {
        int selection = (new Input("1 2 3 4")).checkIntInList("" +
                donator.monitorOffers() +
                "--> To select an offer enter 1.\n" +
                "--> To remove all your offers enter 2.\n" +
                "--> To commit your offers enter 3.\n" +
                "--> To go back enter 4.");
        if (selection == 1) return 21;
        else if (selection == 2) return 22;
        else if (selection == 3) return 40;
        else return 0;
    }
    
    public int showRequestsOptions(Beneficiary beneficiary)
    {
        int selection = (new Input("1 2 3 4")).checkIntInList("" +
                beneficiary.monitorRequests() +
                "--> To select a request enter 1.\n" +
                "--> To remove all your requests enter 2.\n" +
                "--> To commit your requests enter 3.\n" +
                "--> To go back enter 4.");
        if (selection == 1) return 21;
        else if (selection == 2) return 22;
        else if (selection == 3) return 40;
        else return 0;
    }
    
    public String modifyOffersGetName(Donator donator)
    {
        String input = "back";
        for(RequestDonation rd: donator.getOffersList().getRdEntities())
        {
            input += "\t" + rd.getEntity().getName();
        }
        String selection = new Input(input, "\t").checkStrInList("" +
                donator.monitorOffers() +
                "--> Enter the name of the offer you wish to select.\n" +
                "--> To go back enter \"back\".");
        return selection;
    }
    
    public String modifyRequestsGetName(Beneficiary beneficiary)
    {
        String input = "back";
        for(RequestDonation rd: beneficiary.getRequestsList().getRdEntities())
        {
            input += "\t" + rd.getEntity().getName();
        }
        String selection = new Input(input, "\t").checkStrInList("" +
                beneficiary.monitorRequests() +
                "--> Enter the name of the request you wish to select.\n" +
                "--> To go back enter \"back\".");
        return selection;
    }
    
    public String getOfferEntityName(int category)
    {                  
        return Input.strInput(
                       (category == 0?
                       Organization.getOrg().strMaterials() +
                       "--> To donate a material enter its name.\n":
                       Organization.getOrg().strServices() +
                       "--> To provide a service enter its name.\n") +
                       "--> To go back enter \"back\".");
    }
    
    
    public String getRequestEntityName(int category)
    {                
        return Input.strInput(
                       (category == 0?
                       Organization.getOrg().strMaterials() +
                       "--> To request a material enter its name.\n":
                       Organization.getOrg().strServices() +
                       "--> To request a service enter its name.\n") +
                       "--> To go back enter \"back\".");                       
    }
    
    public int confirm()
    {
        return (new Input("1 2")).checkIntInList("--> To confirm enter 1.\n" +
                                               "--> To cancel enter 2.\n");
    }
    
    public void welcome()
    {
        Input.clearConsole();
        System.out.println("===============================\n"+
                           "Welcome to our donation system!\n"+
                           "===============================");
    }
    
    public void bye()
    {
        Input.clearConsole();
        System.out.println("===========================================\n"+
                           "Thank you for trusting us! Have a nice day!\n"+
                           "===========================================");
    }
    
    public String getPhone()
    {
        String phone = (Input.checkPhone("Enter your phone number or enter 'exit' if you want to exit."));
        return phone;
    }
    
    
    public int register(Organization org, String phone)
    {
        String name = Input.strInput("Enter your name.");

        int selection = (new Input("1 2")).checkIntInList("--> To become a donator enter 1.\n"+
                                                            "--> To become a beneficiary enter 2.");

        int no = 0;
        
        if(selection==2)
            no = Input.intInRange("" +
                "Enter the number of individuals" +
                " that your family consists of.", 1, 100);
        
        int confirm = (new Input("1 2")).checkIntInList("--> To confirm your registration enter 1.\n"+
                                                       "--> To cancel your registration enter 2.");
        
        if (confirm == 1 && selection == 1) {
            return org.insertDonator(new Donator(name, phone));
            
        } else if (confirm == 1 && selection == 2) {
            Beneficiary benef = new Beneficiary(name, phone);
            benef.setNoPersons(no);
            return org.insertBeneficiary(benef);
        } else return 0;
        
    }
    
    public int login(String phone, Organization org) 
    {
        String account = org.whatIsThisPhone(phone);
        if (account.equals("donator")) return 3;
        else if (account.equals("beneficiary")) return 4;
        else if (account.equals("admin")) return 5;
        else {System.out.println("BUG!!!"); return -1;}
    }
    
    
    public int donatorStartMenu()
    {
        int selection = (new Input("1 2 3 4 5")).checkIntInList(""+
                                "-->To add an offer enter 1.\n"+
                                "-->To view your offers enter 2.\n"+
                                "-->To commit your offers enter 3.\n"+
                                "-->To logout enter 4.\n"+
                                "-->To exit enter 5.\n");
        if (selection == 1) {
            return 1;
        } else if (selection == 2) {
            return 20;
        } else if (selection == 3) {
            return 40;
        } else if (selection == 4) {
            return -1;
        } else {
            return -2;
        }
    }
    
    public int beneficiaryStartMenu()
    {
        int selection = (new Input("1 2 3 4 5")).checkIntInList(""+
                                "-->To request a donation enter 1.\n"+
                                "-->To view your requests enter 2.\n"+
                                "-->To commit your requests enter 3.\n"+
                                "-->To logout enter 4.\n"+
                                "-->To exit enter 5.\n");
        if (selection == 1) {
            return 1;
        } else if (selection == 2) {
            return 20;
        } else if (selection == 3) {
            return 40;
        } else if (selection == 4) {
            return -1;
        } else {
            return -2;
        }
    }
    
    public int donatorAddOfferChooseCategory(String categories)
    {
        int selection = (new Input("1 2 3")).checkIntInList(categories +
                                          "--> To offer materials enter 1.\n" +
                                          "--> To offer services enter 2.\n" +
                                          "--> To go back enter 3.");
        if (selection == 1) return 2;
        else if (selection == 2) return 10;
        else return 0;
    }
    
    public int beneficiaryRequestDonationChooseCategory(String categories)
    {
        int selection = (new Input("1 2 3")).checkIntInList(categories +
                                          "--> To request materials enter 1.\n" +
                                          "--> To request services enter 2.\n" +
                                          "--> To go back enter 3.");
        if (selection == 1) return 2;
        else if (selection == 2) return 10;
        else return 0;
    }
    
    public int adminStartMenu()
    {
        int selection = (new Input("1 2 3 4")).checkIntInList(""+
                                "-->To view the current available donations enter 1.\n"+
                                "-->To monitor the organization enter 2.\n"+
                                "-->To logout enter 3.\n"+
                                "-->To exit enter 4.\n");
        if (selection == 1) {
            return 1;
        } else if (selection == 2) {
            return 2;
        } else if (selection == 3) {
            return -1;
        } else {
            return -2;
        }
    }
    
    public int adminChooseCategory(String categories)
    {
        int selection = new Input("1 2 3").checkIntInList(categories +
                                       "-->To view materials enter 1.\n"+
                                       "-->To view services enter 2.\n"+
                                       "-->To go back enter 3.\n");
        if (selection == 1) return 10;
        else if (selection == 2) return 11;
        else return 0;
    }
    
    public String adminViewMaterials()
    {
        String input = "back";
        for(Entity entity: Organization.getOrg().getEntityList())
        {
            if (entity.getCategory() == 0) input += "\t" + entity.getName();
        }
        String selection = new Input(input, "\t").checkStrInList(Organization.getOrg().strMaterials()+
                                       "-->To view an item enter its name.\n"+
                                       "-->To go back enter \"back\".\n");
        return selection;
    }
    
    public String adminViewServices()
    {
        String input = "back";
        for(Entity entity: Organization.getOrg().getEntityList())
        {
            if(entity.getCategory() == 1) input += "\t" + entity.getName();
        }
        String selection = new Input(input, "\t").checkStrInList(Organization.getOrg().strServices()+
                                       "-->To view an item enter its name.\n"+
                                       "-->To go back enter \"back\".\n");
        return selection;
    }
    
    public int adminMonitorOrganization()
    {
        int selection = new Input("1 2 3 4").checkIntInList(""+
                                       "-->To list all beneficiaries enter 1.\n"+
                                       "-->To list all donators enter 2.\n"+
                                       "-->To reset all beneficiaries' received items enter 3.\n"+
                                       "-->To go back enter 4.\n");
        if (selection == 1) return 20;
        else if (selection == 2) return 21;
        else if (selection == 3) return 22;
        else return 0;
    }
    
    public String adminMonitorBeneficiaries()
    {
        String input = "back";
        for(Beneficiary benef: Organization.getOrg().getBeneficiaryList())
        {
            input += " " + benef.getPhone();
        }
        String selection = new Input(input).checkStrInList(Organization.getOrg().listBeneficiaries()+
                                       "\n-->To select a beneficiary enter their phone number.\n"+
                                       "-->To go back enter \"back\".\n");
        return selection;
    }
    
    public int adminManageBeneficiary(Beneficiary benef)
    {
        String s = "'s ";
        if(benef.getName().charAt(benef.getName().length()-1) == 's')
        {
            s = "' ";
        }
        int selection = new Input("1 2 3").checkIntInList(benef.monitorReceived()+
                                       "-->To reset " + benef.getName() + s +
                                       "received items enter 1.\n"+
                                       "-->To delete " + benef.getName() +
                                       " from the system enter 2.\n"+
                                       "-->To go back enter 3.\n");
        if(selection == 1)
        {
            benef.resetReceivedList();
            return 200;
        }
        else if(selection == 2) Organization.getOrg().removeBeneficiary(benef);
        return 20;
    }
    
    public String adminMonitorDonators()
    {
        String input = "back";
        for(Donator donor: Organization.getOrg().getDonatorList())
        {
            input += " " + donor.getPhone();
        }
        String selection = new Input(input).checkStrInList(Organization.getOrg().listDonators()+
                                       "\n-->To select a donator enter their phone number.\n"+
                                       "-->To go back enter \"back\".\n");
        return selection;
    }
    
    public int adminManageDonator(Donator donor)
    {
        String s = "'s ";
        if(donor.getName().charAt(donor.getName().length()-1) == 's')
        {
            s = "' ";
        }
        int selection = new Input("1 2").checkIntInList(donor.monitorOffers()+
                                       "-->To delete " + donor.getName() +
                                       " from the system enter 1.\n"+
                                       "-->To go back enter 2.\n");
        if(selection == 1) Organization.getOrg().removeDonator(donor);
        return 21;
    }
    
    public int handleUnregistered()
    {
        int state;
        int selection = new Input("1 2 3").checkIntInList("\nYou are not registered!\n\n" +
                    "--> To register press 1\n" +
                    "--> To enter another phone number press 2\n" + 
                    "--> To exit press 3\n");
        if (selection == 1) state = 1;
        else if (selection == 2) state = 0;
        else state = -1;
        return state;
    }
    
    public static void notification(String msg, double seconds)
    {
        Input.clearConsole();
        System.out.println(msg);
                                        
        try {
            Thread.sleep((long)seconds*1000);
        } catch (InterruptedException ie) {}
        
        Input.clearConsole();
    }
    
    public void startMenu()
    {
        this.welcome();
        
        int state = 0;
        String phone = "phone";
        
        
        while(state != -1)
        {
            switch (state)
            {
                case 0:
                    phone = this.getPhone();
                    if (phone.equals("exit")) {state = -1; break;}
                    state = Organization.getOrg().checkAccount(phone);
                    
                    if (state==2) break;                    //registered
                    state = this.handleUnregistered();      //unregistered
                    break;
                    
                case 1:
                    state = this.register(Organization.getOrg(), phone);
                    
                    break;
                    
                case 2:
                    state = this.login(phone, Organization.getOrg());
                    break;
                    
                case 3:
                    //donator
                {
                    int innerstate = 0;
                    Donator donator = Organization.getOrg().getDonatorFromPhone(phone);
                    
                    Entity entity;
                    double quantity = 0;
                    
                    System.out.println("Welcome " + donator.getName() + "!!!\n" +"Phone: " +
                                        phone + "\nOrganization: "+Organization.getOrg().getName());
                    
                    while (innerstate >= 0){
                        switch (innerstate){
                            case 0:
                               //donator.printDetails();
                                innerstate = this.donatorStartMenu();
                                break;
                                
                            case 1: //add offer
                                innerstate = this.donatorAddOfferChooseCategory(Organization.getOrg().getCategories());
                                break;
                                
                            case 2: //materials
                                {
                                    String name = this.getOfferEntityName(0);
                                    if (name.equals("back"))
                                        {innerstate = 1; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, 0);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try
                                    {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: " + 
                                            Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                            "\n-->Would you like to donate this item? (y/n)").equals("n")) break;
                                    } catch (RequestDonationException e) {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: 0" +
                                            "\n-->Would you like to donate this item? (y/n)").equals("n")) break;
                                    } 
                                    
                                    quantity = Input.intInRange("Enter the quantity you would like to donate.", 
                                                             1 , Input.pinf);
                                    
                                    try {
                                        donator.addOffer(new RequestDonation(entity, quantity));
                                    } catch (RequestDonationException rde) {
                                        rde.notifyMe();
                                    }
                                    
                                }
                                innerstate = 0;
                                break;
                                
                            
                                
                            
                            case 10: //services
                                {
                                    String name = this.getOfferEntityName(1);
                                    if (name.equals("back"))
                                        {innerstate = 1; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, 1);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try
                                    {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: " + 
                                            Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                            "\n-->Would you like to donate this service? (y/n)").equals("n")) break;
                                    } catch (RequestDonationException e) {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: 0" +
                                            "\n-->Would you like to donate this service? (y/n)").equals("n")) break;
                                    }
                                
                                    quantity = Input.intInRange("Enter the amount of hours you would like to provide.", 
                                                             1 , Input.pinf);
                                    
                                    try {
                                        donator.addOffer(new RequestDonation(entity, quantity));
                                    } catch (RequestDonationException rde) {
                                        rde.notifyMe();
                                    }
                                
                                
                                }
                                innerstate = 0;
                                break;
                                
                                
                                
                            case 20: //show offers
                                    
                                    if (donator.getOffersList().getRdEntities().size() == 0) {
                                        this.notification("There are no offers.", Menu.seconds);
                                        innerstate = 0;
                                        
                                    } else {
                                        innerstate = this.showOffersOptions(donator);
                                    
                                    }
                                        
                                break;
                            
                            case 21: //modify offers
                                {
                                    String name = this.modifyOffersGetName(donator);
                                    if (name.equals("back"))
                                        {innerstate = 20; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, -1);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try{
                                        quantity = donator.getOffersList().get(entity.getId()).getQuantity();
                                    } catch (RequestDonationException e){
                                        quantity = 0;
                                    }
                                    switch(new Input("1 2 3").checkIntInList(entity.toString()+
                                                                   "\nQuantity: " + quantity +
                                                                   "\n-->To modify this offer enter 1." +
                                                                   "\n-->To remove this offer enter 2." +
                                                                   "\n-->To go back enter 3.")){
                                        case 1:
                                                                   
                                            quantity = Input.intInRange("Enter the new quantity you would like to donate.", 
                                                                     1 , Input.pinf);
                                    
                                    
                                            try {
                                                donator.modifyOffer(entity.getId(), quantity);
                                            } catch (RequestDonationException rde) {
                                                rde.notifyMe();
                                            }
                                            break;
                                            
                                        case 2:
                                            try{
                                                donator.removeOffer(entity.getId());
                                            } catch (RequestDonationException e) {}
                                            break;
                                        }
                                    
                                }
                                innerstate = 20;
                                break;
                                
                            case 22: //remove offers
                                donator.resetOffers();
                                this.notification("Offers removed successfully", Menu.seconds);
                                innerstate = 0;
                                break;
                                
                            case 40: //commit
                                if (donator.getOffersList().getRdEntities().size() == 0) {
                                    this.notification("There are no offers.", Menu.seconds);
                                    
                                } else {
                                    this.notification("Your offers were commited successfully.", Menu.seconds);
                                    donator.commitOffers();
                                }
                                innerstate = 0;
                                break;
                            case 41: //logout
                                innerstate = -1;
                                break;
                            case 42: //exit
                                innerstate = -2;
                                break;
                            
                        
                        }
                    }
                    if (innerstate == -2) state = -1;
                    else if (innerstate == -1) state = 0;
                    
                    break;
                }
                
                
                
                
                
                case 4:
                    //beneficiary
                {
                    int innerstate = 0;
                    Beneficiary beneficiary = Organization.getOrg().getBeneficiaryFromPhone(phone);
                    
                    Entity entity;
                    double quantity = 0;
                    
                    System.out.println("Welcome " + beneficiary.getName() + "!!!\n" +"Phone: " +
                                        phone + "\nOrganization: "+Organization.getOrg().getName());
                    
                    while (innerstate >= 0){
                        switch (innerstate){
                            case 0:
                               
                                innerstate = this.beneficiaryStartMenu();
                                break;
                                
                            case 1: //add request
                                innerstate = this.beneficiaryRequestDonationChooseCategory(
                                                                             Organization.getOrg().getCategories());
                                break;
                                
                            case 2: //materials
                                {
                                    String name = this.getRequestEntityName(0);
                                    if (name.equals("back"))
                                        {innerstate = 1; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, 0);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try
                                    {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: " + 
                                            Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                            "\n-->Would you like to request this item? (y/n)").equals("n")) break;
                                    } catch (RequestDonationException e) {
                                        if(new Input("y n").checkStrInList(entity.toString()+ "\nQuantity: 0" +
                                            "\n-->Would you like to request this item? (y/n)").equals("n")) break;
                                    }
                                
                                    quantity = Input.intInRange("Enter the quantity you would like to request.", 
                                                             1 , Input.pinf);
                                    
                                    try {
                                        beneficiary.addRequest(new RequestDonation(entity, quantity));


                                    } catch (RequestDonationException rde) {
                                        rde.notifyMe();
                                    }
                                }
                                innerstate = 0;
                                break;
                                
                            
                                
                            
                            case 10: //services
                                {
                                    String name = this.getRequestEntityName(1);
                                    if (name.equals("back"))
                                        {innerstate = 1; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, 1);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try
                                    {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: " + 
                                            Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                            "\n-->Would you like to request this service? (y/n)").equals("n")) break;
                                    } catch (RequestDonationException e) {
                                        if(new Input("y n").checkStrInList(entity.toString() + "\nQuantity: 0" +
                                            "\n-->Would you like to request this service? (y/n)").equals("n")) break;
                                    }
                                
                                    quantity = Input.intInRange("Enter the amount of hours you would like to request.", 
                                                             1 , Double.POSITIVE_INFINITY);
                                    
                                    try {
                                        
                                        beneficiary.addRequest(new RequestDonation(entity, quantity));
                                        
                                        
                                    } catch (RequestDonationException rde) {
                                        rde.notifyMe();
                                    }
                                }
                                innerstate = 0;
                                break;
                                
                                
                                
                            case 20: //show requests
                                    
                                if (beneficiary.getRequestsList().getRdEntities().size() == 0) {
                                    this.notification("There are no requests.", Menu.seconds);
                                    innerstate = 0;
                                        
                                } else {
                                    innerstate = this.showRequestsOptions(beneficiary);
                                    
                                }
                                        
                                break;
                                
                            case 21: //modify requests
                                {
                                    String name = this.modifyRequestsGetName(beneficiary);
                                    if (name.equals("back"))
                                        {innerstate = 20; break;}
                                
                                    entity = Organization.getOrg().getEntityFromName(name, -1);
                                    
                                    try{
                                        if(entity.getName().equals("error")) 
                                            throw new NotAcceptedValueException();

                                    } catch (NotAcceptedValueException nave) {
                                        nave.notifyMe();
                                        break;
                                    }
                                    
                                    try{
                                        quantity = beneficiary.getRequestsList().get(entity.getId()).getQuantity();
                                    } catch (RequestDonationException e){
                                        quantity = 0;
                                    }
                                    switch(new Input("1 2 3").checkIntInList(entity.toString()+
                                                                   "\nQuantity: " + quantity +
                                                                   "\n-->To modify this request enter 1." +
                                                                   "\n-->To remove this request enter 2." +
                                                                   "\n-->To go back enter 3.")){
                                        case 1:
                                                                   
                                            quantity = Input.intInRange("Enter the new quantity you would like to request.", 
                                                                     1 , Input.pinf);
                                    
                                    
                                            try {
                                                beneficiary.modifyRequest(entity.getId(), quantity);
                                            } catch (RequestDonationException rde) {
                                                rde.notifyMe();
                                            }
                                            break;
                                            
                                        case 2:
                                            try{
                                                beneficiary.removeRequest(entity.getId());
                                            } catch (RequestDonationException e) {}
                                            break;
                                        }
                                    
                                }
                                innerstate = 20;
                                break;
                                
                            case 22: //remove offers
                                beneficiary.resetRequests();
                                this.notification("Requests removed successfully", Menu.seconds);
                                innerstate = 0;
                                break;
                                
                            case 40: //commit
                                if (beneficiary.getRequestsList().getRdEntities().size() == 0) {
                                    this.notification("There are no requests.", Menu.seconds);
                                    
                                } else {
                                    this.notification("Your requests were commited successfully.", Menu.seconds);
                                    beneficiary.commitRequests();
                                }
                                innerstate = 0;
                                break;
                            case 41: //logout
                                innerstate = -1;
                                break;
                            case 42: //exit
                                innerstate = -2;
                                break;
                            
                        
                        }
                    }
                    if (innerstate == -2) state = -1;
                    else if (innerstate == -1) state = 0;
                    
                    break;
                }
                    
                case 5:
                    //admin
                {
                    int innerstate = 0;
                    Admin admin = Organization.getOrg().getAdmin();
                    Beneficiary beneficiary = new Beneficiary("Dummy","404");
                    Donator donator = new Donator("Dummy","404");
                    
                    Entity entity = new Service();
                    double quantity = 0;
                    
                    System.out.println("Welcome (Administrator) " + admin.getName() + "!!!\n" +"Phone: " +
                                        phone + "\nOrganization: "+Organization.getOrg().getName());
                    
                    while (innerstate >= 0){
                        switch (innerstate){
                            case 0:
                                innerstate = this.adminStartMenu();
                                break;
                                
                            case 1: //view
                                innerstate = this.adminChooseCategory(Organization.getOrg().getCategories());
                                break;
                                
                            case 2: //monitor organization
                                innerstate = this.adminMonitorOrganization();
                                break;
                                
                            
                                
                            
                            case 10: //view materials
                                entity = Organization.getOrg().getEntityFromName(this.adminViewMaterials(), 0);
                                innerstate = 100;
                                if(entity.getId() == -1) innerstate = 1;
                                break;
                                
                            case 11: //view services
                                entity = Organization.getOrg().getEntityFromName(this.adminViewServices(), 1);
                                innerstate = 110;
                                if(entity.getId() == -1) innerstate = 1;
                                break;
                            
                            
                            case 20: //list beneficiaries
                                beneficiary = Organization.getOrg().getBeneficiaryFromPhone(
                                                                               this.adminMonitorBeneficiaries());
                                innerstate = 200;
                                if(beneficiary.getName().equals("error")) innerstate = 2;
                                break;
                            
                            case 21: //list donators
                                donator = Organization.getOrg().getDonatorFromPhone(this.adminMonitorDonators());
                                innerstate = 210;
                                if(donator.getName().equals("error")) innerstate = 2;
                                break;
                                
                            case 22: //reset beneficiaries
                                for(Beneficiary benef: Organization.getOrg().getBeneficiaryList())
                                {
                                    benef.resetReceivedList();
                                }
                                this.notification("All beneficiaries' received items have been reset!", Menu.seconds);
                                innerstate = 2;
                                break;
                            
                                
                                
                                
                            case 100:
                                try{
                                    innerstate = new Input("1").checkIntInList(entity.toString() + "\nQuantity: " + 
                                        Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                        "\n-->To go back enter 1.\n") + 9;
                                } catch (RequestDonationException e) {
                                    innerstate = new Input("1").checkIntInList(entity.toString() +
                                                                   "\nQuantity: 0\n-->To go back enter 1.\n") + 9;
                                }
                                break;
                                
                            case 110:
                                try{
                                    innerstate = new Input("1").checkIntInList(entity.toString() + "\nHours: " + 
                                        Organization.getOrg().getCurrentDonations().get(entity.getId()).getQuantity() +
                                        "\n-->To go back enter 1.\n") + 10;
                                } catch (RequestDonationException e) {
                                    innerstate = new Input("1").checkIntInList(entity.toString()+
                                                                   "\nHours: 0\n-->To go back enter 1.\n") + 10;
                                }
                                break;
                                
                            case 200: //beneficiary-specific submenu
                                innerstate = this.adminManageBeneficiary(beneficiary);
                                break;
                                
                            case 210: //donator-specific submenu
                                innerstate = this.adminManageDonator(donator);
                                break;
                            
                        
                        }
                    }
                    if (innerstate == -2) state = -1;
                    else if (innerstate == -1) state = 0;
                    
                    break;
                }
            } 
        }
        
        this.bye();
    }
}
