
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

    private final static QName _GetBookDetails_QNAME = new QName("http://soapws.softeng.sapienza.it/", "getBookDetails");
    private final static QName _GetBookDetailsResponse_QNAME = new QName("http://soapws.softeng.sapienza.it/", "getBookDetailsResponse");
    private final static QName _GetDeliveryDate_QNAME = new QName("http://soapws.softeng.sapienza.it/", "getDeliveryDate");
    private final static QName _GetDeliveryDateResponse_QNAME = new QName("http://soapws.softeng.sapienza.it/", "getDeliveryDateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.uniroma1.generatedsource
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookDetails }
     * 
     */
    public GetBookDetails createGetBookDetails() {
        return new GetBookDetails();
    }

    /**
     * Create an instance of {@link GetBookDetailsResponse }
     * 
     */
    public GetBookDetailsResponse createGetBookDetailsResponse() {
        return new GetBookDetailsResponse();
    }

    /**
     * Create an instance of {@link GetDeliveryDate }
     * 
     */
    public GetDeliveryDate createGetDeliveryDate() {
        return new GetDeliveryDate();
    }

    /**
     * Create an instance of {@link GetDeliveryDateResponse }
     * 
     */
    public GetDeliveryDateResponse createGetDeliveryDateResponse() {
        return new GetDeliveryDateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookDetails }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBookDetails }{@code >}
     */
    @XmlElementDecl(namespace = "http://soapws.softeng.sapienza.it/", name = "getBookDetails")
    public JAXBElement<GetBookDetails> createGetBookDetails(GetBookDetails value) {
        return new JAXBElement<GetBookDetails>(_GetBookDetails_QNAME, GetBookDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookDetailsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBookDetailsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soapws.softeng.sapienza.it/", name = "getBookDetailsResponse")
    public JAXBElement<GetBookDetailsResponse> createGetBookDetailsResponse(GetBookDetailsResponse value) {
        return new JAXBElement<GetBookDetailsResponse>(_GetBookDetailsResponse_QNAME, GetBookDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeliveryDate }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDeliveryDate }{@code >}
     */
    @XmlElementDecl(namespace = "http://soapws.softeng.sapienza.it/", name = "getDeliveryDate")
    public JAXBElement<GetDeliveryDate> createGetDeliveryDate(GetDeliveryDate value) {
        return new JAXBElement<GetDeliveryDate>(_GetDeliveryDate_QNAME, GetDeliveryDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeliveryDateResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDeliveryDateResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soapws.softeng.sapienza.it/", name = "getDeliveryDateResponse")
    public JAXBElement<GetDeliveryDateResponse> createGetDeliveryDateResponse(GetDeliveryDateResponse value) {
        return new JAXBElement<GetDeliveryDateResponse>(_GetDeliveryDateResponse_QNAME, GetDeliveryDateResponse.class, null, value);
    }

}
