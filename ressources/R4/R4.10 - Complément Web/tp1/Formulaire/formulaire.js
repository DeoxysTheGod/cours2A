const submitBtn = document.getElementById('submitBtn');
submitBtn.disabled = true;
// inputs
const form = document.getElementById('form');

const civilities = document.getElementById('civility').querySelectorAll('input');
const name = document.getElementById('name');
const firstname = document.getElementById('firstname');
const email = document.getElementById('email');
const phone = document.getElementById('phone');

const address = document.getElementById('address');
const zip = document.getElementById('zip');
const city = document.getElementById('city');

const cardtypes = document.getElementById('cardtype').querySelectorAll('input');
const cardnumber = document.getElementById('cardnumber');
const crypto = document.getElementById('crypto');

var isNameValid = false;
var isFirstnameValid = false;
var isEmailValid = false;
var isPhoneValid = false;
var isAddressValid = false;
var isZipValid = false;
var isCityValid = false;
var isCardnumberValid = false;
var isCryptoValid = false;

var isCivilityValid = false;
var isCardtypeValid = false;
// Regex
const nameRegex = /^[a-zA-Z]+(?:[-\s][a-zA-Z]+)*$/; // Lettres uniquement avec tiret ou espace entre deux mots, Ex: Jean-Pierre, Jean, Jean-Pierre-Paul
const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // Email valide
const phoneRegex = /^[0-9]{10}$/; // 0 + 9 chiffres
const zipRegex = /^[0-9]{5}$/; // 5 chiffres
const cardnumberRegex = /^[0-9]{16}$/; // 16 chiffres
const cryptoRegex = /^[0-9]{3}$/; // 3 chiffres

// Fonctions

// Vérifie si l'input est valide
function isThisValide(input, booleanFunction) {
    if (!booleanFunction) {
        input.classList.add('is-invalid');
        return false;
    } else {
        input.classList.remove('is-invalid');
        return true;
    }
}

// Vérifie si un input radio est valide
function validateRadioInput(inputs) {
    let isValid = false;
    inputs.forEach(input => {
        if (input.checked) {
            isValid = true;
        }
    });
    return isValid;
}

// Vérifie si l'input est valide avec une regex
function validateWithRegex(input, regex) {
    return regex.test(input.value);
}

// Event listeners
form.addEventListener('change', (event) => {
    if (event.target.tagName === 'INPUT') {
        // Text inputs
        if (event.target.id === 'name') {
            isNameValid = isThisValide(event.target, validateWithRegex(event.target, nameRegex));
        }
        if (event.target.id === 'firstname') {
            isFirstnameValid = isThisValide(event.target, validateWithRegex(event.target, nameRegex));
        }
        if (event.target.id === 'email') {
            isEmailValid = isThisValide(event.target, validateWithRegex(event.target, emailRegex));
        }
        if (event.target.id === 'phone') {
            isPhoneValid = isThisValide(event.target, validateWithRegex(event.target, phoneRegex));
        }
        if (event.target.id === 'address') {
            isAddressValid = isThisValide(event.target, event.target.value.length > 0);
        }
        if (event.target.id === 'zip') {
            isZipValid = isThisValide(event.target, validateWithRegex(event.target, zipRegex));
        }
        if (event.target.id === 'city') {
            isCityValid = isThisValide(event.target, event.target.value.length > 0);
        }
        if (event.target.id === 'cardnumber') {
            isCardnumberValid = isThisValide(event.target, validateWithRegex(event.target, cardnumberRegex));
        }
        if (event.target.id === 'crypto') {
            isCryptoValid = isThisValide(event.target, validateWithRegex(event.target, cryptoRegex));
        }
        // Radio inputs
        if (event.target.parentElement.id === 'civility') {
            isCivilityValid = isThisValide(event.target.parentElement, validateRadioInput(civilities));
        }
        if (event.target.parentElement.id === 'cardtype') {
            isCardtypeValid = isThisValide(event.target.parentElement, validateRadioInput(cardtypes));
        }
        // Enable submit button
        console.log(isNameValid + "\n" + isFirstnameValid + "\n" + isEmailValid + "\n" + isPhoneValid + "\n" + isAddressValid + "\n" + isZipValid + "\n" + isCityValid + "\n" + isCardnumberValid + "\n" + isCryptoValid + "\n" + isCivilityValid + "\n" + isCardtypeValid + "\n")

        submitBtn.disabled = !(isNameValid && isFirstnameValid && isEmailValid && isPhoneValid && isAddressValid && isZipValid && isCityValid && isCardnumberValid && isCryptoValid && isCivilityValid && isCardtypeValid);
    }
});