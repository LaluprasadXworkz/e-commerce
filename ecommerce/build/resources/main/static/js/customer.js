function validateForm() {
  let isValid = true;

  document.querySelectorAll(".error").forEach((el) => (el.textContent = ""));

  const name = document.getElementById("customerName");
  const email = document.getElementById("customerEmailId");
  const contact = document.getElementById("contactNumber");
  const gst = document.getElementById("gstNumber");
  const country = document.getElementById("country");
  const pinCode = document.getElementById("pinCode");
  const mailingAddress = document.getElementById("mailingAddress");
  const state = document.getElementById("state");
  const address1 = document.getElementById("address1");

  if (name.value.trim() === "") {
    document.getElementById("nameError").textContent =
      "Customer Name is required";
    isValid = false;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email.value)) {
    document.getElementById("emailError").textContent = "Invalid email address";
    isValid = false;
  }

  const contactPattern = /^[0-9]{10}$/;
  if (!contactPattern.test(contact.value)) {
    document.getElementById("contactError").textContent =
      "Contact Number must be 10 digits";
    isValid = false;
  }

  if (gst.value.trim() === "") {
    document.getElementById("gstError").textContent = "GST Number is required";
    isValid = false;
  }

  if (country.value === "") {
    document.getElementById("countryError").textContent = "Country is required";
    isValid = false;
  }

  const pinCodePattern = /^[0-9]{6}$/;
  if (!pinCodePattern.test(pinCode.value)) {
    document.getElementById("pinCodeError").textContent =
      "Pin Code must be 6 digits";
    isValid = false;
  }

  if (mailingAddress.value.trim() === "") {
    document.getElementById("mailingAddressError").textContent =
      "Mailing Address is required";
    isValid = false;
  }

  if (state.value === "") {
    document.getElementById("stateError").textContent = "State is required";
    isValid = false;
  }

  if (address1.value.trim() === "") {
    document.getElementById("address1Error").textContent =
      "Address Line 1 is required";
    isValid = false;
  }

  return isValid;
}

function clearForm() {
  document.getElementById("customerForm").reset();
  document.querySelectorAll(".error").forEach((el) => (el.textContent = ""));
}

document.addEventListener("DOMContentLoaded", function () {
  fetchCountries();
});

async function fetchCountries() {
  try {
    const response = await fetch(
      "https://countriesnow.space/api/v0.1/countries"
    );
    const data = await response.json();
    const countryDropdown = document.getElementById("country");
    data.data.forEach((country) => {
      const option = document.createElement("option");
      option.value = country.country;
      option.textContent = country.country;
      countryDropdown.appendChild(option);
    });
  } catch (error) {
    console.error("Error fetching countries:", error);
  }
}

async function loadStates() {
  const selectedCountry = document.getElementById("country").value;
  if (selectedCountry) {
    try {
      const response = await fetch(
        "https://countriesnow.space/api/v0.1/countries/states",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ country: selectedCountry }),
        }
      );
      const data = await response.json();
      const stateDropdown = document.getElementById("state");
      stateDropdown.innerHTML = '<option value="">Select State</option>';
      data.data.states.forEach((state) => {
        const option = document.createElement("option");
        option.value = state.name;
        option.textContent = state.name;
        stateDropdown.appendChild(option);
      });
    } catch (error) {
      console.error("Error fetching states:", error);
    }
  }
}

function customerNameCheck() {
  let customerName = document.getElementById("customerName").value;
  const request = new XMLHttpRequest();
  request.open(
    "GET",
    "http://localhost:8080/api/customer/checkCustomerName/" + customerName
  );
  request.send();
  request.onload = function () {
    let returnValue = this.responseText;
    console.log(returnValue);
    document.getElementById("nameError").innerHTML = returnValue;
  };
}

function customerEmailIdCheck() {
  let customerEmailId = document.getElementById("customerEmailId").value;
  const request = new XMLHttpRequest();
  request.open(
    "GET",
    "http://localhost:8080/api/customer/checkCustomerEmail/" + customerEmailId
  );
  request.send();
  request.onload = function () {
    let returnValue = this.responseText;
    console.log(returnValue);
    document.getElementById("emailError").innerHTML = returnValue;
  };
}

function contactNumberCheck() {
  let contactNumber = document.getElementById("contactNumber").value;
  const request = new XMLHttpRequest();
  request.open(
    "GET",
    "http://localhost:8080/api/customer/checkCustomerContactNumber/" +
      contactNumber
  );
  request.send();
  request.onload = function () {
    let returnValue = this.responseText;
    console.log(returnValue);
    document.getElementById("contactError").innerHTML = returnValue;
  };
}

function gstNumberCheck() {
  let gstNumber = document.getElementById("gstNumber").value;
  const request = new XMLHttpRequest();
  request.open(
    "GET",
    "http://localhost:8080/api/customer/checkCustomerGstNumber/" + gstNumber
  );
  request.send();
  request.onload = function () {
    let returnValue = this.responseText;
    console.log(returnValue);
    document.getElementById("gstError").innerHTML = returnValue;
  };
}
