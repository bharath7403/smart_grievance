const BASE_URL = "https://glandless-unhaggled-sharonda.ngrok-free.dev";

// SUBMIT GRIEVANCE
function submitGrievance() {

    const grievance = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        department: document.getElementById("department").value,
        urgency: document.getElementById("urgency").value
    };

    fetch(`${BASE_URL}/api/grievance/submit`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(grievance)
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("response").innerText =
            "✅ Grievance submitted successfully. Your ID: " + data.id;
    })
    .catch(() => alert("❌ Backend not reachable"));
}

// CHECK STATUS BY ID
function checkStatus() {

    const id = document.getElementById("gid").value;

    fetch(`${BASE_URL}/api/grievance/${id}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(g => {
            document.getElementById("statusResult").innerText =
                `Status: ${g.status}
Department: ${g.department}
Urgency: ${g.urgency}`;
        })
        .catch(() => {
            document.getElementById("statusResult").innerText =
                "❌ Invalid Grievance ID or backend unreachable";
        });
}
