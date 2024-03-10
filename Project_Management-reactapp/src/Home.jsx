import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';

export default function Home({projectList}){
    const handleDetele= async (id)=>{
        const response=await fetch(`http://localhost:8090/api/project/delete?pId=${id}`,{method:'DELETE'});
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
    }
    return(
    <div>
        <div className="flex flex-col justify-center mx-32 ">
        {projectList.map((project) => (
            <li key={project.projectId} className='list-none'>
                <div className="flex flex-row items-center *:m-1">
                    <span className='flex-shrink-0 font-semibold text-3xl pr-2 w-[200px]'>{project.name}</span>
                    <span className='flex-shrink-0 font-semibold text-md pr-2 w-[200px]'>{project.developerName}</span>
                    <span className='flex-shrink-0 font-semibold text-md pr-2 w-[250px]'>{project.category}</span>
                    <span className={`flex-shrink-0 text-md pr-2 ${project.status==='completed' ? 'text-gradient-3':'text-amber-500'} w-[150px]`}>{project.status}</span>
                    <span className='flex-shrink-0 text-md pr-2 w-[300px]'>Link: {project.link}</span>
                    <Link to={`/update/${project.projectId}`} className='flex-shrink-0 bg-gradient-to-br from-teal-300 to-green-400 rounded-full hover:scale-105 px-3'>Edit</Link>
                    <button className='flex-shrink-0 bg-gradient-to-br from-pink-500 to bg-red-500 rounded-full hover:scale-105 px-3' onClick={()=>{handleDetele(project.projectId)}}>Delete</button>
                </div>
                <hr />
            </li>
        ))}

        </div>
        <Link to="/new"><div className='w-full text-center p-1'> <button className='bg-blue-500 w-[10vw] font-bold p-2 text-lg text-gray-200 hover:text-white rounded-lg hover:bg-blue-600 hover:scale-105'>New +</button></div></Link>
    </div>
    )
}
Home.propTypes = {
    projectList: PropTypes.array.isRequired,
}