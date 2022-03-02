import React, { Component } from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Star from "./components/Star";

const App = () => {
    return (
        <div className="App">
            <Navbar />
            <div className="container mt-5 pt-1">
                <Sidebar />
                <div className="row mt-5">
                    <div className="col-md-3">{/* <Star /> */}</div>
                </div>
            </div>
        </div>
    );
};

export default App;
