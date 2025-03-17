// Function to reset a section
function resetSection(resultDivId, inputId = null) {
    document.getElementById(resultDivId).innerHTML = ''; // Clear results

    if (inputId) {
        const inputElement = document.getElementById(inputId);
        if (inputElement) {
            if (inputElement.tagName === "SELECT") {
                inputElement.selectedIndex = 0; // Reset dropdown to default
            } else {
                inputElement.value = ''; // Clear text input
            }
        }
    }
}

// Helper function to show loading state
function showLoading(elementId) {
    document.getElementById(elementId).style.display = 'block';
}

// Helper function to hide loading state
function hideLoading(elementId) {
    document.getElementById(elementId).style.display = 'none';
}

// Helper function to display error messages
function showError(elementId, message) {
    const element = document.getElementById(elementId);
    element.innerHTML = `<p style="color: red;">${message}</p>`;
}

// Fetch all users
function fetchAllUsers() {
    const allUsersDiv = document.getElementById('all-users');
    allUsersDiv.innerHTML = ''; // Clear previous results
    showLoading('loading-all-users');

    fetch('http://localhost:8080/api/users')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch users');
            }
            return response.json();
        })
        .then(data => {
            hideLoading('loading-all-users');
            allUsersDiv.innerHTML = '<h3>All Users:</h3>';
            data.forEach(user => {
                allUsersDiv.innerHTML += `
                    <div class="user-card">
                        <p><strong>ID:</strong> ${user.id}</p>
                        <p><strong>Name:</strong> ${user.name}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Role:</strong> ${user.role}</p>
                        <p><strong>Department:</strong> ${user.department ? user.department.name : 'N/A'}</p>
                    </div>
                `;
            });
        })
        .catch(error => {
            hideLoading('loading-all-users');
            showError('all-users', error.message);
        });
}

// Fetch user by ID
function fetchUserById() {
    const userId = document.getElementById('userId').value;
    if (!userId) {
        alert('Please enter a valid User ID');
        return;
    }

    const userByIdDiv = document.getElementById('user-by-id');
    userByIdDiv.innerHTML = ''; // Clear previous results
    showLoading('loading-user-by-id');

    fetch(`http://localhost:8080/api/users/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('User not found');
            }
            return response.json();
        })
        .then(user => {
            hideLoading('loading-user-by-id');
            userByIdDiv.innerHTML = `
                <h3>User Details:</h3>
                <div class="user-card">
                    <p><strong>ID:</strong> ${user.id}</p>
                    <p><strong>Name:</strong> ${user.name}</p>
                    <p><strong>Email:</strong> ${user.email}</p>
                    <p><strong>Role:</strong> ${user.role}</p>
                    <p><strong>Department:</strong> ${user.department ? user.department.name : 'N/A'}</p>
                </div>
            `;
        })
        .catch(error => {
            hideLoading('loading-user-by-id');
            showError('user-by-id', error.message);
        });
}

// Fetch users by role
function fetchUsersByRole() {
    const role = document.getElementById('role').value.toUpperCase(); // Ensure uppercase
    const usersByRoleDiv = document.getElementById('users-by-role');
    usersByRoleDiv.innerHTML = ''; // Clear previous results
    showLoading('loading-users-by-role');

    fetch(`http://localhost:8080/api/users/by-role?role=${role}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to fetch users by role: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            hideLoading('loading-users-by-role');
            usersByRoleDiv.innerHTML = '<h3>Users by Role:</h3>';
            data.forEach(user => {
                usersByRoleDiv.innerHTML += `
                    <div class="user-card">
                        <p><strong>ID:</strong> ${user.id}</p>
                        <p><strong>Name:</strong> ${user.name}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Role:</strong> ${user.role}</p>
                        <p><strong>Department:</strong> ${user.department ? user.department.name : 'N/A'}</p>
                    </div>
                `;
            });
        })
        .catch(error => {
            hideLoading('loading-users-by-role');
            showError('users-by-role', error.message);
        });
}


// Fetch users by name
function fetchUsersByName() {
    const name = document.getElementById('userName').value;
    if (!name) {
        alert('Please enter a name');
        return;
    }

    const usersByNameDiv = document.getElementById('users-by-name');
    usersByNameDiv.innerHTML = ''; // Clear previous results
    showLoading('loading-users-by-name');

    fetch(`http://localhost:8080/api/users/by-name?name=${name}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch users by name');
            }
            return response.json();
        })
        .then(data => {
            hideLoading('loading-users-by-name');
            usersByNameDiv.innerHTML = '<h3>Users by Name:</h3>';
            data.forEach(user => {
                usersByNameDiv.innerHTML += `
                    <div class="user-card">
                        <p><strong>ID:</strong> ${user.id}</p>
                        <p><strong>Name:</strong> ${user.name}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Role:</strong> ${user.role}</p>
                        <p><strong>Department:</strong> ${user.department ? user.department.name : 'N/A'}</p>
                    </div>
                `;
            });
        })
        .catch(error => {
            hideLoading('loading-users-by-name');
            showError('users-by-name', error.message);
        });
}