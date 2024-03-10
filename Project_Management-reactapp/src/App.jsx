import { useState } from "react";
import Home from "./Home";
import { Link } from "react-router-dom";
// import NavBar from "./components/Nav";
import {Route,Routes} from "react-router-dom";
import New from "./New";
import Update from "./Update";
import { useEffect } from "react";

export default function App() {
  const [projectList, setProjectList] = useState([]);
  const [isLoad, setIsLoad] = useState(false);
  const getData = async () => {
    setIsLoad(true);
    const res = await fetch("http://localhost:8090/api/projects");
    const data = await res.json();
    setProjectList(data);
    setIsLoad(false);
  }
  useEffect(( )=>{
    getData();
  },[])
  return (
<div className="flex flex-col *:my-2">
  <h1 className="font-bold text-gradient-1 text-center text-4xl p-1 flex flex-row justif">
    <Link to="/" className="px-10"><img src="/home-button.png" alt=""  width={30} height={30}/></Link>
    <span className="px-96">Project Management</span>
  </h1>
  <Routes>
    {/* <Route path="/" element={ <Home projectList={projectList} />} /> */}
    <Route path="/" element={  isLoad ? <div className="flex justify-center items-center"><img src="/loading_soon.gif" alt="Loading GIF" className="loading-animation" /> </div>: <Home projectList={projectList} />} />
    <Route path="/new" element={<New />} />
    <Route path="/update/:id" element={<Update projectList={projectList} />} />
  </Routes>
</div>

  );
}