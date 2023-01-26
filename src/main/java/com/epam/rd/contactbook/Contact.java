package com.epam.rd.contactbook;
public class Contact {
    private String contactName;
    private ContactInfo contactNumber;
    private final ContactInfo personName;

    private final Email[] emailAdres;
    private final Social[] socialMediaLink;
    private int emailAddressCount;
    private int socialMediaCount;
    public Contact(String contactName) {
        this.contactName = contactName;
        this.personName = this.new NameContactInfo();
        this.emailAdres = new Contact.Email[3];
        this.socialMediaLink = new Contact.Social[5];
        this.emailAddressCount = 0;
        this.socialMediaCount = 0;
    }
    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return Contact.this.contactName;
        }

        @Override
        public String toString() {
            return this.getTitle() + " : " + this.getValue();
        }
    }

    public static class Email implements ContactInfo {
        private final String value;

        public Email(String value) {
            super();
            this.value = value;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.getTitle() + " : " + this.getValue();
        }
    }

    public static class Social implements ContactInfo {
        private final String title;
        private final String value;

        public Social(String title, String value) {
            super();
            this.title = title;
            this.value = value;
        }

        @Override
        public String getTitle() {
            return this.title;
        }

        @Override
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.getTitle() + " : " + this.getValue();
        }
    }



    public void rename(String newName) {
         if (newName != null && !newName.trim().equals(""))
            Contact.this.contactName = newName;
    }

    public Email addEmail(String localPart, String domain) {

        if (this.emailAddressCount < 3) {
            Email email = new Email(localPart + "@" + domain);
            this.emailAdres[this.emailAddressCount++] = email;
            return email;
        }
            return null;
    }

    public Email addEpamEmail(String firstname, String lastname) {
          if (this.emailAddressCount < 3) {
            Email email = new Email(firstname + "_" + lastname + "@epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            this.emailAdres[this.emailAddressCount++] = email;
            return email;
        }
            return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {

        if (this.contactNumber == null) {
            ContactInfo contactInfo = new ContactInfo() {
                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
                @Override
                public String getTitle() {
                    return "Tel";
                }
            };
            this.contactNumber = contactInfo;
            return contactInfo;
        }
            return null;
    }

    public Social addTwitter(String twitterId) {

        if (this.socialMediaCount < 5) {
            Social social = new Social("Twitter", twitterId);
            this.socialMediaLink[this.socialMediaCount++] = social;
            return social;
        }
            return null;
    }

    public Social addInstagram(String instagramId) {
        if (this.socialMediaCount < 5) {
            Social social = new Social("Instagram", instagramId);
            this.socialMediaLink[this.socialMediaCount++] = social;
            return social;
        }
            return null;


    }

    public Social addSocialMedia(String title, String id) {

        if (this.socialMediaCount < 5) {
            Social social = new Social(title, id);
            this.socialMediaLink[this.socialMediaCount++] = social;
            return social;
        }
            return null;
    }

    public ContactInfo[] getInfo() {

        int counter = 0;
        if (this.personName != null) counter++;

        if (this.contactNumber != null) counter++;

        counter += (this.socialMediaCount) + (this.emailAddressCount);

        ContactInfo[] contactInfo = new ContactInfo[counter];

        int i = 0;
        if (this.personName != null) contactInfo[i++] = personName;

        if (this.contactNumber != null) contactInfo[i++] = this.contactNumber;

        for (int j=0; j<this.emailAddressCount; j++) contactInfo[i++] = this.emailAdres[j];
        for (int j=0; j<this.socialMediaCount; j++)  contactInfo[i++] = this.socialMediaLink[j];

        return contactInfo;
    }

}