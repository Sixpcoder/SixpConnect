package com.example.sixpconnect.model

class Usermodel {
    var photo   :String?=null
    var name   :String?=null
    var email   :String?=null
    var password   :String?=null

    constructor()
    constructor(photo: String?, name: String?, email: String?, password: String?) {
        this.photo = photo
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }




}