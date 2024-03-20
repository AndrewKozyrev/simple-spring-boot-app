const usersTable = document.getElementById('users-table');
const createButton = document.getElementById('create-button');
const deleteButton = document.getElementById('delete-button');

// Function to create a table row for a user
function createUserRow(user) {
  const row = document.createElement('tr');
  row.innerHTML = `
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.surname}</td>
    <td>${user.age}</td>
    <td>
      <button data-user-id="${user.id}">Delete</button>
    </td>
  `;
  return row;
}

// Fetch user data (replace with your actual logic to fetch users)
fetch('/users') // Replace with your endpoint to get users
  .then(response => response.json())
  .then(users => {
    users.forEach(user => {
      const row = createUserRow(user);
      usersTable.tbody.appendChild(row);
    });
  });

// Handle create button click (replace with your desired redirect logic)
createButton.addEventListener('click', () => {
  window.location.href = '/user-form';
});

// Handle delete button click
usersTable.addEventListener('click', (event) => {
  if (event.target.tagName === 'BUTTON') {
    const userId = event.target.dataset.userId;
    if (userId) {
      // Replace with your logic to send a DELETE request
      fetch(`/delete/${userId}`, { method: 'DELETE' })
        .then(() => {
          // User deleted successfully, remove row from table
          event.target.parentElement.parentElement.remove();
          deleteButton.disabled = true; // Disable delete button if no user selected
        })
        .catch(error => console.error(error));
    }
  }
});

// Enable delete button only when a user row is clicked
usersTable.addEventListener('click', (event) => {
  if (event.target.tagName === 'TD') {
    deleteButton.disabled = false;
  } else {
    deleteButton.disabled = true;
  }
});
