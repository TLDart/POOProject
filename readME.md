##Structure for the files to the read by the program

####Teacher
    Teacher,Name,email,id,field,number

####Scholar

    PhD      |
    Master   | -> Name,email,id,startDate(dd-mm-yyyy),endDate(dd-mm-yy),(comma separated coordinators, except on PhD ofc)
    Graduate |

####Project

    Project,name,nick,startDate(dd-mm-yyyy),duration(int),endTime(dd-mm-yyyy or null if the project is not yet ended),mainTeacher,/teachers,_Scholars

#####Task

    {TaskName},id,startDate(dd-mm-yyyy),estimatedFinish(dd-mm-yyyy),endTime(dd-MM-yyyy or null if not yet finished),Project,status ,responsible
    
    {TaskName} = Development, Desgin, Documentation
    
######In order for the project to load, the one must add Items according to the folowing order Peaple,project and finnaly tasks

