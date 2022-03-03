import React from "react";

const Star = () => {
    const fetchStars = async () => {
        const request = await fetch("http://localhost:8080/api/v1/galaxies");
        const response = await request.json();
        console.log(response[0]);
    };

    return (
        <div className="starCard shadow p-3 mb-5 bg-white rounded">
            <div className="starCard-body">
                <h5 className="card-title font-weight-bold">Sent by:</h5>
                <p className="starCard-text">
                    Some quick example text to build on the card title and make
                    up the bulk of the card's content.
                </p>
                <img src="..." className="starCard-img" alt="..." />
                <a href="#" className="btn btn-primary" onClick={fetchStars}>
                    Go somewhere
                </a>
            </div>
        </div>
    );
};

export default Star;
