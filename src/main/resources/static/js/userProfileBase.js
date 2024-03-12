// Get all navbar anchor tags
const navLinks = document.querySelectorAll('.nav-link');

// Function to handle click event
function handleClick(event) {
    // Prevent default link behavior
    event.preventDefault();

    // Remove 'active' class from all links
    navLinks.forEach(link => {
        link.classList.remove('active');
    });

    // Add 'active' class to clicked link
    event.target.classList.add('active');

    // Navigate to the href of the clicked link
    window.location.href = event.target.href;
}

// Add click event listener to each link
navLinks.forEach(link => {
    link.addEventListener('click', handleClick);

    // Check if the href of the link matches the current page URL
    if (link.href === window.location.href) {
        link.classList.add('active');
    }
});


function logout() {
    // Make an AJAX request to trigger the void method
    fetch('/logout', {
        method: 'POST'
    })
        .then(() => {
            console.log('Logout successfully');
        })
        .catch(error => {
            console.error('Error when logout', error);
        });
}


