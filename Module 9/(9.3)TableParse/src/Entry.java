import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
    String type;
    String number;
    String currency;
    Date   date;
    String reference;
    String description;
    double expense;
    double income;

    public Entry(String type, String number, String currency,Date date, String reference, String description, double income, double expense) {
        setType(type);
        setNumber(number);
        setCurrency(currency);
        setDate(date);
        setReference(reference);
        setDescription(description);
        setIncome(income);
        setExpense(expense);
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getIncome() { return income; }
    public void setIncome(double income) { this.income = income; }

    public double getExpense() { return expense; }
    public void setExpense(double expense) { this.expense = expense; }

    public String toString() {
        return type + " - " + number + " - " + currency + " - " +
                (new SimpleDateFormat("dd.MM.yyyy")).format(date) + " - " +
                reference + " - " + description.replaceAll(" {2,}", " ") + " - " + income + " - " + expense;
    }

    public String getPlace() { return getDescription().substring(17, 54).trim(); }
}
