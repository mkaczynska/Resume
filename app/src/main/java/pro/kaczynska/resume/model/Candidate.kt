package pro.kaczynska.resume.model

import android.graphics.drawable.Drawable

class Candidate {
    var fullName: String = ""
    var phoneNumber: String = ""
    var emailAddress: String = ""
    var linkedInId: String = ""
    var skypeName: String = ""
    lateinit var photo: Drawable
}