# coupons.ejb
<a>The EJB module of the Coupzone</a>

Contains EJBs for managing the Incomes related to the Coupzone project purchases.

* Uses: Message Driven Bean, Stateless Session Bean, JPA (using DB of Wildfly11) and more..

For execution:
1. Build project.
2. Deploy module on Wildfly11, use standalone-full.xml (for MDB support).

For testings a Url is published by Wildfly11:
http://localhost:8080/coupons.ejb/TestDAO?wsdl

Example of test request:
Use Postman like software to send HTTP SOAP requests.
1. use the URL of the WSDL and HTTP POST type request:
http://localhost:8080/coupons.ejb/TestDAO?wsdl
2. add HTTP Header
Content-Type=text/xml
3. use Body:
<s11:Envelope xmlns:s11='http://schemas.xmlsoap.org/soap/envelope/'>
  <s11:Body>
    <ns1:storeIncome xmlns:ns1='http://testers.com/'>
<!-- optional -->
      <invokerID>1</invokerID>
<!-- optional -->
      <timestamp>2018-02-23</timestamp>
<!-- optional -->
      <amount>1</amount>
<!-- optional -->
      <description>COMPANY_UPDATE</description>
    </ns1:storeIncome>
  </s11:Body>
</s11:Envelope>
