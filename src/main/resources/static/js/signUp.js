
// Toggle Switch
// Get the toggle switch element
const toggleSwitch = document.getElementById('toggleSwitch');

// Function to handle page change after toggle switch action
function handleChange() {

    let currentPage = window.location.href;

    if (currentPage.includes('userSignUp')) {
        window.open('http://localhost:8080/clinicSignUp', "_self"); // if the current page url is "userSignUp" then the page will be changed to "clinicSignUp"
    } else if(currentPage.includes('clinicSignUp')){
        window.open('http://localhost:8080/userSignUp', "_self"); // if the current page url is "clinicSignUp" then the page will be changed to "userSignUp"
    } else{
        window.open('http://localhost:8080/userSignUp', "_self");
    }

    updateToggleSwitch();

}



// Function to update the toggle switch state based on the current page URL
function updateToggleSwitch() {
    var currentPage = window.location.href;

    if (currentPage.includes('clinicSignUp')) {
        toggleSwitch.checked = true; // If on patientSignUp page, toggle switch is checked
    } else if (currentPage.includes('userSignUp')) {
        toggleSwitch.checked = false; // If on DoctorSignUp page, toggle switch is unchecked
    }
}


// Add event listener to the toggle switch
toggleSwitch.addEventListener('change', handleChange);

// Call the updateToggleSwitch function to initialize the toggle switch state
updateToggleSwitch();