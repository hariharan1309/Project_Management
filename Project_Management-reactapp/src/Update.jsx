// import PropTypes from "prop-types";
import { useParams } from "react-router-dom";
import { useState,useEffect } from "react";

const convertDateToInputFormat = (inputDate) => {
  const date = new Date(inputDate);
  const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const year = date.getFullYear();

  return `${day}-${month}-${year}`;
};

export default function Update(){
  const { id } = useParams();
  const [pname, setPname] = useState("");
  const [dname, setDname] = useState("");
  const [category, setCategory] = useState("");
  const [status, setStatus] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [link, setLink] = useState("");
  const [description, setDescription] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await fetch(`http://localhost:8090/api/project?pId=${id}`);
        const data = await res.json();
        // setProject(data);
        setPname(data.name);
        setDname(data.developerName);
        setCategory(data.category);
        setStatus(data.status);
        setStartDate(data.startDate);
        setEndDate(data.endDate);
        setLink(data.link);
        setDescription(data.description);
      } catch (error) {
        console.error('Error fetching project data:', error);
      }
    };

    fetchData();
  }, [id]);

    const handleInputChange = (e) => {
      const { name, value } = e.target;
      switch (name) {
        case 'pname':
          setPname(value);
          break;
        case 'dname':
          setDname(value);
          break;
        case 'category':
            setCategory(value);
          break;
        case 'description':
          setDescription(value);
          break;
        case 'link':
          setLink(value);
          break;
        case 'startDate':
            setStartDate(value);
            break;
        case 'endDate':
            setEndDate(value);
            break;
        case 'status':
            setStatus(value);
            break;
        default:
          break;
      }
    };
    const handleSubmit = (e) => {
      e.preventDefault();
      const projectData = {
        "projectId": id,
        "name": pname,
        "developerName": dname,
        "category": category,
        "status": status, // or 'completed'
        "link": link,
        "startDate": convertDateToInputFormat(startDate),
        "endDate": convertDateToInputFormat(endDate),
        "description": description
      };
      console.log(projectData);
      
      fetch("http://localhost:8090/api/project/update", {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          // Add any other headers if needed
        },
        body: JSON.stringify(projectData)
      })
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json(); // Assuming the server returns JSON data
        })
        .then(data => {
          // Handle the successful response
          console.log('Success:', data);
          
        })
        .catch(error => {
          // Handle errors during the fetch
          console.error('Error:', error);
        });
      console.log("Updated");
  }
    return(
    <>
    <div>
        <form className='flex hover:shadow-2xl shadow-purple-500 mx-10 box-border rounded-xl' onSubmit={handleSubmit}>
          <div className='flex flex-col w-[400px] max-md:w-max mx-10  px-5 py-5 *:form-container text-gray-800 box-border'>
            <div className='flex flex-row max-md:flex-col'>
              
              <span className='w-[150px]'>
                <label htmlFor="pname">Project Name <span className='text-red-700 font-bold'>*</span></label>
              </span>
              <input type="text" name="pname" id="pname" value={pname} onChange={handleInputChange} 
              />
            </div>

            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="dname">Developer Name<span className='text-red-700 font-bold'>*</span></label>
              </span>
              <input type="text" name="dname" id="dname" value={dname} onChange={handleInputChange}/>
            </div>

            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="category">Category <span className='text-red-700 font-bold'>*</span></label>
              </span>
              <input type="text" name="category" id="category" value={category} onChange={handleInputChange} />

            </div>
            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="status">Status <span className="text-red-700 font-bold">*</span></label>
              </span>
              <select name="status" id="status" onChange={handleInputChange} value={status}>
                <option value="completed" >completed</option>
                <option value="ongoing">ongoing</option>
              </select>
            </div>
            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="link">Link </label>
              </span>
              <input type="url" name="link" id="link" value={link} onChange={handleInputChange}/>
            </div>
            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="startDate">StartDate</label>
              </span>
              <input type="date" name="startDate" id="startDate" value={startDate} onChange={handleInputChange} />
            </div>
            <div className='flex flex-row max-md:flex-col'>
            <span className='w-[150px]'>
                <label htmlFor="endDate">End date</label>
              </span>
              <input type="date" name="endDate" id="endDate" value={endDate} onChange={handleInputChange} />
            </div>

            <div className='flex flex-col'>
              <span className='w-[150px]'>
                  <label htmlFor="description">Description</label>
                </span>
              <textarea name="description" id="description" cols="30" rows="10" value={description} onChange={handleInputChange}
              placeholder='Enter your message here...'
              ></textarea>
            </div>
            <button type="submit" className='bg-white my-2 mx-8 rounded-3xl hover:text-black hover:mx-5 hover:border-blue-400 border-2'>
              ADD
            </button>   
            </div>
        </form>
    </div>
    </>
    )
}
