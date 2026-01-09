function submitGrievance() {
    const data = {
        name: name.value,
        email: email.value,
        title: title.value,
        description: description.value
    };

    fetch("http://localhost:8080/api/grievance/submit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        response.innerText =
            "Grievance submitted successfully. Your ID is " + result.id;
    });
}

function checkStatus() {
    const id = gid.value;

    fetch("http://localhost:8080/api/grievance/all")
    .then(res => res.json())
    .then(data => {
        const g = data.find(x => x.id == id);
        if (g) {
            statusResult.innerText =
                `Status: ${g.status} | Department: ${g.department} | Urgency: ${g.urgency}`;
        } else {
            statusResult.innerText = "Invalid Grievance ID";
        }
    });
}
