import http from "../http-common";

const getAllContacts = (params) => {
    return http.get("/contacts", { params });
};


const ContactService = {
    getAllContacts
};
  
export default ContactService;