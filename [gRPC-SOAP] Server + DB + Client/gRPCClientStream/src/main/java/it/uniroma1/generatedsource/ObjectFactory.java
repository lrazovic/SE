
package it.uniroma1.generatedsource;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.uniroma1.generatedsource package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Book_QNAME = new QName("http://soapws.uniroma1.it/", "book");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.uniroma1.generatedsource
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Book.Sellers }
     * 
     */
    public Book.Sellers createBookSellers() {
        return new Book.Sellers();
    }

    /**
     * Create an instance of {@link Book.Sellers.Entry }
     * 
     */
    public Book.Sellers.Entry createBookSellersEntry() {
        return new Book.Sellers.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Book }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Book }{@code >}
     */
    @XmlElementDecl(namespace = "http://soapws.uniroma1.it/", name = "book")
    public JAXBElement<Book> createBook(Book value) {
        return new JAXBElement<Book>(_Book_QNAME, Book.class, null, value);
    }

}
