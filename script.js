function submitGrievance() {

    const data = {
        name: name.value,
        email: email.value,
        title: title.value,
        description: description.value
    };

    fetch("http://localhost:8080/api/grievance/submit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(result => {
        response.innerText =
            "✅ Grievance submitted successfully. Your ID is " + result.id;
        response.style.color = "green";
        response.style.fontWeight = "bold";
    })
    .catch(() => {
        response.innerText = "❌ Failed to submit grievance";
        response.style.color = "red";
    });
}

function checkStatus() {

    const id = gid.value;

    fetch(`http://localhost:8080/api/grievance/${id}`)
    .then(res => {
        if (!res.ok) throw new Error();
        return res.json();
    })
    .then(g => {

        statusResult.style.fontWeight = "bold";

        if (g.status === "RESOLVED") {
            statusResult.style.color = "green";
        } 
        else if (g.status === "PENDING") {
            statusResult.style.color = "orange";
        } 
        else {
            statusResult.style.color = "red"; // SUBMITTED
        }

        statusResult.innerText =
            `Status: ${g.status} | Department: ${g.department} | Urgency: ${g.urgency}`;
    })
    .catch(() => {
        statusResult.style.color = "red";
        statusResult.style.fontWeight = "bold";
        statusResult.innerText = "❌ Invalid Grievance ID or backend unreachable";
    });
}
