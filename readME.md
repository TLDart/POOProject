##Structure for the files to the read by the program

####Teacher
    Teacher,Name,email,id,field,number

####Scholar

    PhD      |
    Master   | -> Name,email,id,startDate(dd-mm-yyyy),endDate(dd-mm-yy),(comma separated coordinators)
    Graduate |

####Project

    Project,name,nick,startDate(dd-mm-yyyy),estimatedFinish(dd-mm-yy),mainTeacher,/teachers,_Scholars

#####Task

    {TaskName},id,startDate(dd-mm-yyyy),estimatedFinish(dd-mm-yy),Project, responsible
    
######In order for the project to load, the one must add Items according to the folowing order Peaple,project and finnaly tasks

