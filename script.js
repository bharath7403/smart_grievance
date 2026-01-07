const BASE_URL = "https://glandless-unhaggled-sharonda.ngrok-free.dev";

// SUBMIT GRIEVANCE
function submitGrievance() {

    const grievance = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        title: document.getElementById("title").value,
        description: document.getElementById("description").value
    };

    fetch(`${BASE_URL}/api/grievance/submit`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(grievance)
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("response").innerText =
            "Grievance submitted successfully. ID: " + data.id;
    })
    .catch(() => alert("Backend not reachable"));
}

// CHECK STATUS
function checkStatus() {

    const gid = document.getElementById("gid").value;

    fetch(`${BASE_URL}/api/grievance/all`)
    .then(res => res.json())
    .then(data => {

        const g = data.find(x => x.id == gid);

        document.getElementById("statusResult").innerText = g
            ? `Status: ${g.status}\nDepartment: ${g.department}\nUrgency: ${g.urgency}`
            : "Invalid Grievance ID";
    })
    .catch(() => alert("Backend not reachable"));
}
