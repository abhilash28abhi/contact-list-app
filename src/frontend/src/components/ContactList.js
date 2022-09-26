import React, { Component } from "react";
import ContactService from "../services/ContactService";

import Pagination from "@material-ui/lab/Pagination";


export default class ContactList extends Component {

    constructor(props) {
        super(props);
        this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
        this.handleKeyDown = this.handleKeyDown.bind(this);
        this.retrieveContacts = this.retrieveContacts.bind(this);
        this.setActiveContact = this.setActiveContact.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handlePageSizeChange = this.handlePageSizeChange.bind(this);

        this.state = {
            contacts: [],
            currentIndex: -1,
            currentContact: null,
            searchTitle: "",
            page: 1,
            count: 0,
            pageSize: 5,
        };
        this.pageSizes = [5, 10, 20];
    }

    componentDidMount() {
        //console.log('called component did mount')
        this.retrieveContacts();
    }

    onChangeSearchTitle(e) {
        const searchTitle = e.target.value;
        this.setState({
            searchTitle: searchTitle,
        });
    }
    //populate the request params for API
    getRequestParams(searchTitle, page, pageSize) {
        let params = {};

        if (searchTitle) {
            params["name"] = searchTitle;
        }
        if (page) {
            params["page"] = page - 1;
        }
        if (pageSize) {
            params["size"] = pageSize;
        }

        return params;
    }

    retrieveContacts() {
        const { searchTitle, page, pageSize } = this.state;
        const params = this.getRequestParams(searchTitle, page, pageSize);

        ContactService.getAllContacts(params)
            .then((response) => {
                const { contacts, totalPages } = response.data;

                this.setState({
                    contacts: contacts,
                    count: totalPages,
                });
                console.log(response.data);
            })
            .catch((e) => {
                console.log(e);
            });
    }

    handlePageChange(event, value) {
        this.setState(
            {
                page: value,
            },
            () => {
                this.retrieveContacts();
            }
        );
    }

    handleKeyDown (event) {
        if (event.key === 'Enter') {
            //console.log('enter pressed')
            this.retrieveContacts();
        }
    }

    handlePageSizeChange(event) {
        this.setState(
            {
                pageSize: event.target.value,
                page: 1
            },
            () => {
                this.retrieveContacts();
            }
        );
    }

    setActiveContact(contact, index) {
        this.setState({
            currentContact: contact,
            currentIndex: index,
        });
    }
    

    render() {
        const {
            searchTitle,
            contacts,
            currentIndex,
            page,
            count,
            pageSize,
        } = this.state;

        return (
            <div className="list row">
                <div className="col-md-8">
                    <div className="input-group mb-3">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Search by name"
                            value={searchTitle}
                            onChange={this.onChangeSearchTitle}
                            onKeyDown={this.handleKeyDown}
                        />
                        <div className="input-group-append">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.retrieveContacts}
                            >
                                Search
                            </button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <h4>Contacts List</h4>

                    <div className="mt-3">
                        {"Items per Page: "}
                        <select onChange={this.handlePageSizeChange} value={pageSize}>
                            {this.pageSizes.map((size) => (
                                <option key={size} value={size}>
                                    {size}
                                </option>
                            ))}
                        </select>

                        <Pagination
                            className="my-3"
                            count={count}
                            page={page}
                            siblingCount={1}
                            boundaryCount={1}
                            variant="outlined"
                            shape="rounded"
                            onChange={this.handlePageChange}
                        />
                    </div>

                    <ul className="list-group">
                        {contacts &&
                            contacts.map((contact, index) => (
                                <li
                                    className={
                                        "list-group-item " +
                                        (index === currentIndex ? "active" : "")
                                    }
                                    onClick={() => this.setActiveContact(contact, index)}
                                    style={{textAlign:'left'}}
                                    key={index}
                                >
                                    {contact.name}
                                    <img src={contact.url} alt="Contact" height="50" width="50" style={{float:'right'}}/>
                                </li>
                            ))}
                    </ul>
                </div>
            </div>
        );
    }
}