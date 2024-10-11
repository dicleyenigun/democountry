package com.example.democountry.service;

import com.example.democountry.models.Country;
import org.oorsprong.websamples.*;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryInfoServiceHandler {

    private static final String WSDL_URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl";

    public List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();
        try {
            URL url = new URL(WSDL_URL);
            CountryInfoService service = new CountryInfoService(url);
            // SOAP protokolü ile iletişim kuracak nesne oluşturuluyor.
            CountryInfoServiceSoapType soap = service.getCountryInfoServiceSoap();

            // 'listOfCountryNamesByCode' metodunu kullanarak liste alıyoruz
            ArrayOftCountryCodeAndName countryNamesArray = soap.listOfCountryNamesByCode();


            // Listeyi alıp döngü ile işliyoruz
            List<TCountryCodeAndName> countryNamesList = countryNamesArray.getTCountryCodeAndName();
            for (TCountryCodeAndName country : countryNamesList) {
                String code = country.getSISOCode();
                String name = country.getSName();    // Ülke ismini al

                // fullCountryInfo ile her ülkenin dil bilgilerini de al
                TCountryInfo countryInfo = soap.fullCountryInfo(code);

                // Dillerin alınması
                List<String> languages = new ArrayList<>();
                if(countryInfo.getLanguages() != null){
                    for(TLanguage language : countryInfo.getLanguages().getTLanguage()){
                        languages.add(language.getSName()); // dillerin isimlerini ekliyoruz.
                    }
                }


                countries.add(new Country(code, name, languages)); // Country nesnesini ekliyoruz
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    public Country getCountryDetails(String code) {
        try {
            URL url = new URL(WSDL_URL);
            CountryInfoService service = new CountryInfoService(url);
            CountryInfoServiceSoapType soap = service.getCountryInfoServiceSoap();

            // FullCountryInfo'dan TCountryInfo'ya geçiyoruz
            TCountryInfo countryInfo = soap.fullCountryInfo(code); // Bu metot, TCountryInfo döndürmeli

            List<String> languages = new ArrayList<>();

            // Dillerin alınması
            if (countryInfo.getLanguages() != null) {
                for (TLanguage language : countryInfo.getLanguages().getTLanguage()) {
                    languages.add(language.getSName());
                }
            }

            // Country nesnesini döndür
            return new Country(countryInfo.getSISOCode(), countryInfo.getSName(), languages);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
