document.getElementById('add-employee-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission
    function resetForm() {
        document.getElementById('add-employee-form').reset();
    }

// Ensure the script runs after the DOM is fully loaded
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelector(".reset-btn").addEventListener("click", resetForm);
    });

    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value,
        departmentId: document.getElementById('department').value ? parseInt(document.getElementById('department').value) : null
    };

    fetch('http://localhost:8080/api/users', {  // Ensure backend URL is correct
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // Important: Send as JSON
        },
        body: JSON.stringify(formData), // Convert form data to JSON
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('result-message').innerText = 'Employee added successfully!';
        })
        .catch((error) => {
            document.getElementById('result-message').innerText = 'Error adding employee: ' + error.message;
        });
});
