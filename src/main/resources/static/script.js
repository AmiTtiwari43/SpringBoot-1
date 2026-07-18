const BASE_URL = "http://localhost:8080";

let operations = 0;

// Elements
const loader = document.getElementById("loader");
const result = document.getElementById("result");
const historyList = document.getElementById("historyList");
const counter = document.getElementById("counter");
const lastOperation = document.getElementById("lastOperation");
const status = document.getElementById("status");

// ----------------------------
// Utility Functions
// ----------------------------

function showLoader() {
    loader.classList.remove("hidden");
}

function hideLoader() {
    loader.classList.add("hidden");
}

function showResult(data) {
    result.textContent =
        typeof data === "string"
            ? data
            : JSON.stringify(data, null, 2);
}

function updateDashboard(operation, success = true) {

    operations++;
    counter.textContent = operations;

    lastOperation.textContent = operation;

    status.textContent = success ? "Success" : "Failed";
    status.className = success ? "status success" : "status error";

}

function addHistory(message) {

    if (historyList.children.length === 1 &&
        historyList.children[0].textContent === "No operations yet.") {
        historyList.innerHTML = "";
    }

    const li = document.createElement("li");

    li.textContent =
        `${new Date().toLocaleTimeString()} - ${message}`;

    historyList.prepend(li);

}

function studentData() {

    return {

        id: Number(document.getElementById("id").value),

        name: document.getElementById("name").value,

        age: Number(document.getElementById("age").value),

        department: document.getElementById("department").value

    };

}

function displayStudent(student) {

    document.getElementById("studentName").textContent =
        student.name ?? "-";

    document.getElementById("displayId").textContent =
        student.id ?? "-";

    document.getElementById("displayAge").textContent =
        student.age ?? "-";

    document.getElementById("displayDepartment").textContent =
        student.department ?? "-";

}

function clearFields() {

    document.getElementById("id").value = "";
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    document.getElementById("department").value = "";

}

async function request(url, method, body = null) {

    showLoader();

    const options = {
        method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    try {

        const response = await fetch(url, options);

        const contentType =
            response.headers.get("content-type");

        let data;

        if (contentType &&
            contentType.includes("application/json")) {

            data = await response.json();

        } else {

            data = await response.text();

        }

        hideLoader();

        if (!response.ok) {
            throw new Error(typeof data === "string" ? data : JSON.stringify(data));
        }

        return data;

    } catch (error) {

        hideLoader();

        throw error;

    }

}

// ----------------------------
// CRUD Operations
// ----------------------------

async function createStudent() {

    try {

        const student = studentData();

        const data = await request(
            `${BASE_URL}/create`,
            "POST",
            student
        );

        showResult(data);
        displayStudent(data);

        updateDashboard("CREATE");
        addHistory("Created Student");

    } catch (e) {

        showResult(e.message);

        updateDashboard("CREATE", false);

    }

}

async function getStudent() {

    try {

        const id = document.getElementById("id").value;

        const data = await request(
            `${BASE_URL}/get/${id}`,
            "GET"
        );

        showResult(data);

        displayStudent(data);

        updateDashboard("GET");

        addHistory(`Fetched Student ${id}`);

    } catch (e) {

        showResult(e.message);

        updateDashboard("GET", false);

    }

}

async function updateStudent() {

    try {

        const student = studentData();

        const data = await request(
            `${BASE_URL}/put/${student.id}`,
            "PUT",
            student
        );

        showResult(data);

        displayStudent(data);

        updateDashboard("UPDATE");

        addHistory(`Updated Student ${student.id}`);

    } catch (e) {

        showResult(e.message);

        updateDashboard("UPDATE", false);

    }

}

async function patchStudent() {

    try {

        const id = document.getElementById("id").value;

        const student = {};

        if (name.value) {
            student.name = name.value;
        }

        if (age.value) {
            student.age = Number(age.value);
        }

        if (department.value) {
            student.department = department.value;
        }

        const data = await request(
            `${BASE_URL}/patch/${id}`,
            "PATCH",
            student
        );

        showResult(data);

        displayStudent(data);

        updateDashboard("PATCH");

        addHistory(`Patched Student ${id}`);

    } catch (e) {

        showResult(e.message);

        updateDashboard("PATCH", false);

    }

}

async function deleteStudent() {

    try {

        const id = document.getElementById("id").value;

        const data = await request(
            `${BASE_URL}/delete/${id}`,
            "DELETE"
        );

        showResult(data);

        displayStudent({
            id: "-",
            name: "No Student Selected",
            age: "-",
            department: "-"
        });

        updateDashboard("DELETE");

        addHistory(`Deleted Student ${id}`);

    } catch (e) {

        showResult(e.message);

        updateDashboard("DELETE", false);

    }

}

// ----------------------------
// Keyboard Shortcuts
// ----------------------------

document.addEventListener("keydown", function (e) {

    if (e.key === "Enter") {
        createStudent();
    }

    if (e.key === "Escape") {
        clearFields();
    }

});